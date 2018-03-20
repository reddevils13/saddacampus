package com.example.win10.collegeblog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity
{
    private DatabaseReference databaseReference;
    private RecyclerView bloglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bloglist=(RecyclerView)findViewById(R.id.bloglist);
        bloglist.setHasFixedSize(true);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("New Post");

        bloglist.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Blog> options=new FirebaseRecyclerOptions.Builder<Blog>().setQuery(databaseReference,Blog.class).build();

        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                options) {
            @Override
            protected void onBindViewHolder(@NonNull BlogViewHolder holder, int position, @NonNull Blog model) {

                holder.setTitle(model.getTitle());
                holder.setDescription(model.getDescription());
                holder.setImageUri(getApplicationContext(),model.getImageUri());
            }


            @Override
            public BlogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
                return new BlogViewHolder(view);
            }

        };
        firebaseRecyclerAdapter.startListening();
        bloglist.setAdapter(firebaseRecyclerAdapter);
    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder{

        View mView;
        Blog blog;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void setTitle(String title)
        {
            TextView posttitle=(TextView)mView.findViewById(R.id.posttitle);
            posttitle.setText(title);
        }
        public void setDescription(String description)
        {
            TextView posttext=(TextView) mView.findViewById(R.id.posttext);
            posttext.setText(description);
        }
        public  void setImageUri(Context context,String uri)
        {
            ImageView postimage=(ImageView)mView.findViewById(R.id.postimage);
            Picasso.get().load(uri).into(postimage);


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add)
            startActivity(new Intent(MainActivity.this,PostActivity.class));
        return super.onOptionsItemSelected(item);
    }
}
