package com.mutinda.myvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    ImageView profileImageView;
    TextView usernameTextView;
    ImageButton shareProfile;
    TextView developerUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImageView = findViewById(R.id.profileImageView);
        usernameTextView = findViewById(R.id.usernameTextView);
        shareProfile = findViewById(R.id.shareProfile);
        developerUrl = findViewById(R.id.developerUrl);

        //Getting the intent extras sent from the MainActivity
        Intent intent = getIntent();
        final String userName = intent.getStringExtra(DevelopersAdapter.KEY_NAME);
        String image = intent.getStringExtra(DevelopersAdapter.KEY_IMAGE);
        final String profileUrl = intent.getStringExtra(DevelopersAdapter.KEY_URL);
        //Setting the views
        Picasso.with(this).load(image).into(profileImageView);

        usernameTextView.setText(userName);
        developerUrl.setText(profileUrl);

        //Set an onClickListener to developerUrl so as to open the developer link using implicit intent
        developerUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = profileUrl;
                Intent i  = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        //Set an onClickListener to the ImageButton shareProfile and implement implicit intent for sharing developer's profile
        shareProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer " + userName + ", " + profileUrl);
                Intent chooser  = Intent.createChooser(shareIntent, "Share via");
                if(shareIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(chooser);
                }
            }
        });
    }
}