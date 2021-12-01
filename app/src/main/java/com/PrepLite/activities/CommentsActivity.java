package com.PrepLite.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.PrepLite.OnItemClickListener;
import com.PrepLite.R;
import com.PrepLite.adapters.commentAdapter;
import com.PrepLite.models.Comment;
import com.PrepLite.models.User;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    private ArrayList<Comment> comment_list;
    private RecyclerView recyclerView;
    private commentAdapter commentAdapter;
    private String date;
    private String time;
    private ImageButton comment_send;
    private EditText comment;
    private String comment_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        getSupportActionBar().setTitle("Comments");
        getSupportActionBar().setHomeButtonEnabled(false);

        buildRecyclerView();
        comment = findViewById(R.id.comment_text);
        comment_send = findViewById(R.id.comment_send);
        comment_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comment_text = comment.getText().toString().trim();
                if(comment_text.length()==0)
                {
                    Toast.makeText(CommentsActivity.this, "Cannot post empty comment", Toast.LENGTH_SHORT).show();
                }
                else
                {
//                    date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
//                    time = new SimpleDateFormat("HH:mm").format(new Date());
                    comment_list.add(new Comment(new User("Username"),comment_text,"28/11/2021 19:20"));
                    //backend code to add this comment to database
                    commentAdapter.notifyItemInserted(comment_list.size());
                    comment.setText("");
                }
            }
        });

    }

    private void buildRecyclerView()
    {
        comment_list = new ArrayList<>();
        recyclerView = findViewById(R.id.comments_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        commentAdapter = new commentAdapter(comment_list,this);
        recyclerView.setAdapter(commentAdapter);

        commentAdapter.setOnCommentClickListener(new OnItemClickListener() {
            @Override
            public void OnItemLongClicked(int position) {
                super.OnItemLongClicked(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(CommentsActivity.this);
                builder.setTitle("Delete comment")
                        .setMessage("Are you sure you want to delete this comment?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //backend code to delete this comment from db
                                commentAdapter.notifyItemRemoved(position);
                                comment_list.remove(position);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.create().show();
            }
        });
    }

}