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

import br.edu.uniritter.recyclerview.AlbumDetail;
import br.edu.uniritter.recyclerview.R;
import br.edu.uniritter.recyclerview.model.Album;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    List<Album> albumsList;

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        public View viewAlbum;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewAlbum = itemView;
        }
    }

    public AlbumAdapter(List<Album> albums) {
        this.albumsList = albums;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_album, parent, false);
        return new AlbumViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AlbumViewHolder holder, int position) {
        final Album obj = this.albumsList.get(position);
        TextView userId = holder.viewAlbum.findViewById(R.id.albumUserId);
        userId.setText("User ID: "+obj.getUserId());
        TextView id = holder.viewAlbum.findViewById(R.id.albumId);
        id.setText("ID: "+obj.getId()+"");
        TextView title = holder.viewAlbum.findViewById(R.id.albumTitle);
        title.setText(obj.getTitle());
        CardView cv = holder.viewAlbum.findViewById(R.id.cardAlbum);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.viewAlbum.getContext(), AlbumDetail.class);
                intent.putExtra("albumObj", obj);
                holder.viewAlbum.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.albumsList.size();
    }
}
