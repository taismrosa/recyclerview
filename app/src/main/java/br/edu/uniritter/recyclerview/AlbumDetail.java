package br.edu.uniritter.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import br.edu.uniritter.recyclerview.model.Album;

public class AlbumDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        Intent intent = getIntent();
        Album album = intent.getParcelableExtra("albumObj");
        bind(album);
    }

    private void bind(Album a) {
        TextView userId = findViewById(R.id.albumUserId);
        userId.setText("User ID: "+a.getUserId());
        TextView id = findViewById(R.id.albumId);
        id.setText("ID: "+a.getId());
        TextView title = findViewById(R.id.albumTitle);
        title.setText(a.getTitle());
    }
}