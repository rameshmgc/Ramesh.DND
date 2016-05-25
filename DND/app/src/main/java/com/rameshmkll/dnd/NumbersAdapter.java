package com.rameshmkll.dnd;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebHistoryItem;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MRamesh on 19-05-2016.
 */
public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumbersViewHolder> {
ArrayList<NumbersPojo> numbersPojo=null;
 Activity activty;

    public NumbersAdapter(ArrayList<NumbersPojo> arrayList,Activity activity){
        this.numbersPojo=arrayList;
        this.activty=activity;
    }
    @Override
    public NumbersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v=activty.getLayoutInflater().inflate(R.layout.adap_number,parent,false);
       return new NumbersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NumbersViewHolder holder, int position) {
final NumbersPojo numbersPojo=this.numbersPojo.get(position);
        holder.number.setText(numbersPojo.number);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             AlertDialog.Builder alertBuilder=new AlertDialog.Builder(activty);
                alertBuilder.setItems(new String[]{"Edit", "DELETE"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase sqLiteDatabase=MyApplication.getDatabase(activty);
                        switch (which){

                            case 0:
                                ContentValues contentValues=new ContentValues();
                                contentValues.put("PHONE_NUMBER", "1111111");
                                sqLiteDatabase.update("PHONE_NUMBERS",contentValues,"ID=?",new String[]{String.valueOf(numbersPojo.id)});
                                break;
                            case 1:
                                sqLiteDatabase.delete("PHONE_NUMBERS","ID=?",new String[]{String.valueOf(numbersPojo.id)});
                                break;
                        }
                    }
                });
                alertBuilder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return numbersPojo.size();
    }

    public class NumbersViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        CardView cardView;
        public NumbersViewHolder(View itemView) {
            super(itemView);
            number= (TextView) itemView.findViewById(R.id.tvnumber);
            cardView= (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
