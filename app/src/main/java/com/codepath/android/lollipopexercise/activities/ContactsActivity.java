package com.codepath.android.lollipopexercise.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.android.lollipopexercise.R;
import com.codepath.android.lollipopexercise.adapters.ContactsAdapter;
import com.codepath.android.lollipopexercise.models.Contact;
import com.google.android.material.snackbar.Snackbar;

import org.parceler.Parcels;

import java.util.List;


public class ContactsActivity extends AppCompatActivity {
    private RecyclerView rvContacts;
    private ContactsAdapter mAdapter;
    private List<Contact> contacts;

    // for snackbar
    private CoordinatorLayout rlMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // for snackbar
        rlMainContent = findViewById(R.id.rlMainContent);

        // Find RecyclerView and bind to adapter
        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // allows for optimizations
        rvContacts.setHasFixedSize(true);

        // Define 2 column grid layout
        final GridLayoutManager layout = new GridLayoutManager(ContactsActivity.this, 2);

        // Unlike ListView, you have to explicitly give a LayoutManager to the RecyclerView to position items on the screen.
        // There are three LayoutManager provided at the moment: GridLayoutManager, StaggeredGridLayoutManager and LinearLayoutManager.
        rvContacts.setLayoutManager(layout);

        // get data
        contacts = Contact.getContacts();

        // Create an adapter
        mAdapter = new ContactsAdapter(ContactsActivity.this, contacts);

        // Bind adapter to list
        rvContacts.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent = new Intent(this, DetailsActivity.class);

        intent.putExtra("contact", Parcels.wrap(id));

        return super.onOptionsItemSelected(item);
    }

    public void onComposeAction (MenuItem mi) {
        Contact contact = Contact.getRandomContact(this);
        contacts.add(0, contact);
        mAdapter.notifyItemInserted(0);

        // Displaying a snackbar
        Snackbar.make(rlMainContent,"Contact Added!", Snackbar.LENGTH_LONG)
                .setActionTextColor(ContextCompat.getColor(ContactsActivity.this, R.color.accent))
                .setDuration(3000).show(); // Don’t forget to show!


    }


}
