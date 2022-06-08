package com.codepath.android.lollipopexercise.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.android.lollipopexercise.R;
import com.codepath.android.lollipopexercise.models.Contact;

import org.parceler.Parcels;

public class DetailsActivity extends AppCompatActivity {
    public static final String EXTRA_CONTACT = "EXTRA_CONTACT";
    private Contact mContact;
    private ImageView ivProfile;
    private TextView tvName;
    private TextView tvPhone;
    private View vPalette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ivProfile = (ImageView) findViewById(R.id.ivProfile);
        tvName = (TextView) findViewById(R.id.tvName);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        vPalette = findViewById(R.id.vPalette);

        // Extract contact from bundle
        mContact = (Contact)getIntent().getExtras().getSerializable(EXTRA_CONTACT);

        // Fill views with data
        Glide.with(DetailsActivity.this).load(mContact.getThumbnailDrawable()).centerCrop().into(ivProfile);
        tvName.setText(mContact.getName());
        tvPhone.setText(mContact.getNumber());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Contact contact = Parcels.unwrap(getIntent().getParcelableExtra("contact"));
        tvName.setText(contact.getName());
        tvPhone.setText(contact.getNumber());
        Glide.with(this).load(contact.getThumbnailDrawable()).into(ivProfile);



        return super.onOptionsItemSelected(item);
    }

}
