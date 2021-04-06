package br.edu.uniritter.recyclerview.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.uniritter.recyclerview.PostDetail;
import br.edu.uniritter.recyclerview.R;
import br.edu.uniritter.recyclerview.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> postsList;
    private int layout;

    public class PostViewHolder extends RecyclerView.ViewHolder {
        public View viewPost;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewPost = itemView;
        }
    }

    public PostAdapter(List<Post> posts, int layout) {
        this.postsList = posts;
        this.layout = layout;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {
        if (this.postsList.get(position) instanceof Post) {
            final Post obj = (Post) this.postsList.get(position);
            CardView cv = holder.viewPost.findViewById(R.id.cardPost);
            cv.setTag(obj);
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.viewPost.getContext(), PostDetail.class);
                    intent.putExtra("postObj", obj);
                    holder.viewPost.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.postsList.size();
    }
}
