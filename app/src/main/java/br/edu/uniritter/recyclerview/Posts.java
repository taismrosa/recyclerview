package br.edu.uniritter.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.recyclerview.adapter.PostAdapter;
import br.edu.uniritter.recyclerview.model.Post;

public class Posts extends AppCompatActivity implements Response.Listener<JSONArray>, Response.ErrorListener {
    List<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/posts";
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        queue.add(jsonRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String msg = error.getMessage();
        Toast.makeText(this.getApplicationContext(),"ERRO: "+msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONArray response) {
        try {
            for(int i = 0; i < response.length(); i++) {
                JSONObject json = response.getJSONObject(i);
                Post obj = new Post(
                        json.getInt("userId"),
                        json.getInt("id"),
                        json.getString("title"),
                        json.getString("body")
                );
                posts.add(obj);
            }

            RecyclerView rv = findViewById(R.id.rvPosts);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            rv.setLayoutManager(llm);

            PostAdapter postAdapter = new PostAdapter(posts);
            rv.setAdapter(postAdapter);
        }
        catch (JSONException e) {
            Log.e("ERRO ",e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}