package com.example.mohammed.refugeecare.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mohammed.refugeecare.R;
import com.example.mohammed.refugeecare.adapters.CustomListviewAdapter;

/**
 * Created by Ahmed Khattab 2 on 10/19/2016.
 */
public class CharityProfile extends AppCompatActivity {


    private TextView txtcharityName;
    private TextView txtcharityAddress;
    private TextView txtcharityHelp;
    private TextView txtcharityNotes;
    private TextView txtcharityPapers;


    private String charityName;
    private String charityAddress;
    private String charityHelp;
    private String charityNotes;
    private String charityPapers;
    private String charityPhones [];

    private ListView listPhones;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charity_profile);


        txtcharityName      = (TextView) findViewById (R.id.text_name_charity_profile);
        txtcharityAddress   = (TextView) findViewById (R.id.text_address_charity_profile);
        txtcharityHelp      = (TextView) findViewById (R.id.text_help_charity_profile);
        txtcharityNotes     = (TextView) findViewById (R.id.text_notes_charity_profile);
        txtcharityPapers     = (TextView) findViewById(R.id.text_papers_charity_profile);



        Intent i = getIntent();
        charityName     = i.getExtras().getString("name");
        charityAddress  = i.getExtras().getString("address");
        charityHelp     = i.getExtras().getString("help");
        charityNotes    = i.getExtras().getString("notes");
        charityPapers  = i.getExtras().getString("papers");
        charityPhones   = i.getStringArrayExtra("phones");



//        listview adapter
        listPhones = (ListView) findViewById(R.id.phones_list_view_charity_profile);
        CustomListviewAdapter adapter =new CustomListviewAdapter(this , charityPhones);
        listPhones.setAdapter(adapter);


        txtcharityName.setText   (charityName   );
        txtcharityAddress.setText(charityAddress);
        txtcharityHelp.setText   (charityHelp   );
        txtcharityNotes.setText  (charityNotes  );
        txtcharityPapers.setText(charityPapers);
        //put data in fieldas


        /**
         *     Intent i = new Intent(context , CharityProfile.class);
         i.putExtra("name",charitiesData.get(position).getCharityName());
         i.putExtra("help",charitiesData.get(position).getCharityTypeOfGive());
         i.putExtra("address",charitiesData.get(position).getCharityAddress());
         i.putExtra("notes",charitiesData.get(position).getCharityNotes());
         i.putExtra("phones",charitiesData.get(position).getCharityPhones());
         **/
    }
    public void onClick(View view)
    {
        finish();
    }
}
