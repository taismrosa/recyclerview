package br.edu.uniritter.recyclerview.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.uniritter.recyclerview.PostDetail;
import br.edu.uniritter.recyclerview.R;
import br.edu.uniritter.recyclerview.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> postsList;

    public class PostViewHolder extends RecyclerView.ViewHolder {
        public View viewPost;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewPost = itemView;
        }
    }

    public PostAdapter(List<Post> posts) {
        this.postsList = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post, parent, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {
        final Post obj = this.postsList.get(position);
        TextView userId = holder.viewPost.findViewById(R.id.postUserId);
        userId.setText("User ID: "+obj.getUserId());
        TextView id = holder.viewPost.findViewById(R.id.postId);
        id.setText("ID: "+obj.getId()+"");
        TextView title = holder.viewPost.findViewById(R.id.postTitle);
        title.setText(obj.getTitle());
        TextView body = holder.viewPost.findViewById(R.id.postBody);
        body.setText(obj.getBody());
        CardView cv = holder.viewPost.findViewById(R.id.cardPost);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.viewPost.getContext(), PostDetail.class);
                intent.putExtra("postObj", obj);
                holder.viewPost.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.postsList.size();
    }
}
