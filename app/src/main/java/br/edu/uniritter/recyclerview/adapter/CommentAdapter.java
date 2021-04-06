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

import br.edu.uniritter.recyclerview.CommentDetail;
import br.edu.uniritter.recyclerview.R;
import br.edu.uniritter.recyclerview.model.Comment;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    List<Comment> commentsList;

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        public View viewComment;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewComment = itemView;
        }
    }

    public CommentAdapter(List<Comment> comments) {
        this.commentsList = comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment, parent, false);
        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentViewHolder holder, int position) {
        final Comment obj = this.commentsList.get(position);
        TextView postId = holder.viewComment.findViewById(R.id.commentPostId);
        postId.setText("Post ID: "+obj.getPostId());
        TextView id = holder.viewComment.findViewById(R.id.commentId);
        id.setText("ID: "+obj.getId()+"");
        TextView name = holder.viewComment.findViewById(R.id.commentName);
        name.setText(obj.getName());
        TextView email = holder.viewComment.findViewById(R.id.commentEmail);
        email.setText(obj.getEmail());
        TextView body = holder.viewComment.findViewById(R.id.commentBody);
        body.setText(obj.getBody());
        CardView cv = holder.viewComment.findViewById(R.id.cardComment);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.viewComment.getContext(), CommentDetail.class);
                intent.putExtra("commentObj", obj);
                holder.viewComment.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.commentsList.size();
    }
}
