package com.mutinda.myvolley;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DevelopersAdapter extends RecyclerView.Adapter<DevelopersAdapter.ViewHolder> {
    //declare DeveloperList private member variable
    private List<DevelopersList> developersList;
    //context variable
    private Context mContext;
    //Keys for our intents
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_URL = "url";

    public DevelopersAdapter(List<DevelopersList> developersList, Context context) {
        this.developersList = developersList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public DevelopersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.developers_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DevelopersAdapter.ViewHolder holder, int position) {
    //create a variable that gets the current instance of the developer in the list
        final DevelopersList currentDeveloper=developersList.get(position);
        //populate the text view and image view with data
        holder.login.setText(currentDeveloper.getLogin());
        holder.html_url.setText(currentDeveloper.getHtml_url());
        //Use the library Picasso to load images to prevent crashing..loading images is resource intensive
        Picasso.with(mContext).load(currentDeveloper.getAvatar_url()).into(holder.avatar_url);
        //Set onClickListener to handle click events
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            //Ensure you override the onClick method
            public void onClick(View view) {
                //Create an instance of the developer list and initialize it
                DevelopersList developersList1 = currentDeveloper;
                //Create an intent and specify the target class as Profile Activity
                Intent skipIntent = new Intent(view.getContext(), ProfileActivity.class);
                //Use intent EXTRA to pass data from MainActivity to ProfileActivity
                skipIntent.putExtra(KEY_NAME, developersList1.getLogin());
                skipIntent.putExtra(KEY_URL, developersList1.getHtml_url());
                skipIntent.putExtra(KEY_IMAGE, developersList1.getAvatar_url());
                view.getContext().startActivity(skipIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        //return the size of developers list
        return developersList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        public TextView login;
        public ImageView avatar_url;
        public TextView html_url;
        public LinearLayout linearLayout;
        // the constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initialize view objects
            login = itemView.findViewById(R.id.username);
            avatar_url = itemView.findViewById(R.id.imageView);
            html_url = itemView.findViewById(R.id.html_url);
            linearLayout = itemView.findViewById(R.id.linear_layout);
        }
    }
}
