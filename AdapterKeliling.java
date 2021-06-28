package com.rrynn.walls4;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rrynn.walls4.databinding.AdapterKelilingBinding;
import com.rrynn.walls4.databinding.EditKelilingBinding;
import com.rrynn.walls4.databinding.EditStockBinding;
import com.rrynn.walls4.databinding.SeeKelilingBinding;
import com.rrynn.walls4.model.ModelKeliling;
import com.rrynn.walls4.model.ModelStock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AdapterKeliling extends RecyclerView.Adapter<AdapterKeliling.AdapterHolderKeliling> {

//    DatabaseReference reference;
//    SharedPreferences preferences;
//    String unique;
//
//    public SharedPreferences getPreferences() {
//        return editStock.getSharedPreferences()
//    }
//
//    public DatabaseReference getReference() {
//        return FirebaseDatabase.getInstance().getReference("DataStock").child(unique);
//    }

    // Keliling
    Context context;
    ArrayList<ModelKeliling> arrayList;
    AdapterKelilingBinding binding;
    String keyId = "";
    String date;
    UpdateKeliling keliling;

    // Stock
    EditStockBinding stockBinding;
    DatabaseReference reference_stock;
    SharedPreferences preferences_stock;
    String unique_stock;
    ModelStock modelStock;
    EditStock editStock;
    Keliling keliling1;
    public String tespadd;


    public AdapterKeliling(Context context,ArrayList<ModelKeliling> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        keliling = (UpdateKeliling) context;

    }

    @NonNull
    @Override
    public AdapterKeliling.AdapterHolderKeliling onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new AdapterHolderKeliling(AdapterKelilingBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKeliling.AdapterHolderKeliling holder, int position) {
        ModelKeliling modelKeliling = arrayList.get(position);
        String date = modelKeliling.getDate();


        holder.binding.dateKeliling.setText(date);




        holder.binding.editKeliling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelKeliling modelKeliling1 = arrayList.get(position);
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                EditKelilingBinding binding = EditKelilingBinding.inflate(layoutInflater);
                //
                LayoutInflater layoutInflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                EditStockBinding binding1 = EditStockBinding.inflate(layoutInflater);
                //
                editKeliling(modelKeliling1,binding,binding1);
            }
        });

        holder.binding.seeKeliling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelKeliling modelKeliling1 = arrayList.get(position);
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                SeeKelilingBinding binding = SeeKelilingBinding.inflate(layoutInflater);
                seeKeliling(modelKeliling1,binding);


            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class AdapterHolderKeliling extends RecyclerView.ViewHolder{

        AdapterKelilingBinding binding;

        public AdapterHolderKeliling(@NonNull AdapterKelilingBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    private void editKeliling(ModelKeliling mk,EditKelilingBinding editKelilingBinding,EditStockBinding editStockBinding){
        Dialog dialog = new Dialog(context);
        View view = editKelilingBinding.getRoot();
        dialog.setContentView(view);
        dialog.show();

        // masuk
        editKelilingBinding.edtMasukPaddlecoklatKeliling.setText(mk.getMasuk_paddlepop_coklat());
        editKelilingBinding.edtMasukPaddlerainbowKeliling.setText(mk.getMasuk_paddlepop_rainbow());
        editKelilingBinding.edtMasukTricoKeliling.setText(mk.getMasuk_paddlepop_trico());
        editKelilingBinding.edtMasukFeastKeliling.setText(mk.getMasuk_feast());
        editKelilingBinding.edtMasukDoraemonKeliling.setText(mk.getMasuk_doraemon());
        editKelilingBinding.edtMasukBananaKeliling.setText(mk.getMasuk_banana());
        editKelilingBinding.edtMasukTwisterKeliling.setText(mk.getMasuk_twister());
        editKelilingBinding.edtMasukMagnumclasicKeliling.setText(mk.getMasuk_magnum_clasic());
        editKelilingBinding.edtMasukMagnumalmondKeliling.setText(mk.getMasuk_magnum_almond());
        editKelilingBinding.edtMasukFeastwichKeliling.setText(mk.getMasuk_feast_wich());
        editKelilingBinding.edtMasukPopulaireKeliling.setText(mk.getMasuk_populaire());
        editKelilingBinding.edtMasukShakyKeliling.setText(mk.getMasuk_shaky());
        editKelilingBinding.edtMasukCornettoclasicoKeliling.setText(mk.getMasuk_cornetto_clasico());
        editKelilingBinding.edtMasukCornettodiscKeliling.setText(mk.getMasuk_cornetto_disc());
        editKelilingBinding.edtMasukCornettooreoKeliling.setText(mk.getMasuk_cornetto_oreo());
        editKelilingBinding.edtMasukCornettosilverKeliling.setText(mk.getMasuk_cornetto_silver());

        // sisa
        editKelilingBinding.edtSisaPaddlecoklatKeliling.setText(mk.getSisa_paddlepop_coklat());
        editKelilingBinding.edtSisaPaddlerainbowKeliling.setText(mk.getSisa_paddlepop_rainbow());
        editKelilingBinding.edtSisaTricoKeliling.setText(mk.getSisa_paddlepop_trico());
        editKelilingBinding.edtSisaFeastKeliling.setText(mk.getSisa_feast());
        editKelilingBinding.edtSisaDoraemonKeliling.setText(mk.getSisa_doraemon());
        editKelilingBinding.edtSisaBananaKeliling.setText(mk.getSisa_banana());
        editKelilingBinding.edtSisaTwisterKeliling.setText(mk.getSisa_twister());
        editKelilingBinding.edtSisaMagnumclasicKeliling.setText(mk.getSisa_magnum_clasic());
        editKelilingBinding.edtSisaMagnumalmondKeliling.setText(mk.getSisa_magnum_almond());
        editKelilingBinding.edtSisaFeastwichKeliling.setText(mk.getSisa_feast_wich());
        editKelilingBinding.edtSisaPopulaireKeliling.setText(mk.getSisa_populaire());
        editKelilingBinding.edtSisaShakyKeliling.setText(mk.getSisa_shaky());
        editKelilingBinding.edtSisaCornettoclasicoKeliling.setText(mk.getSisa_cornetto_clasico());
        editKelilingBinding.edtSisaCornettodiscKeliling.setText(mk.getSisa_cornetto_disc());
        editKelilingBinding.edtSisaCornettooreoKeliling.setText(mk.getSisa_cornetto_oreo());
        editKelilingBinding.edtSisaCornettosilverKeliling.setText(mk.getSisa_cornetto_silver());




        keyId = mk.getUserId();

//        editKelilingBinding.btnStockKeliling.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        editKelilingBinding.btnEditKeliling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat format = new SimpleDateFormat("dd MMMM YYYY");
                Calendar calendar = Calendar.getInstance();

                date = format.format(calendar.getTime());

                // harga
                int harga_paddle_coklat = 4000;
                int harga_paddle_rainbow = 4000;
                int harga_trico = 3400;
                int harga_feast = 5500;
                int harga_doraemon = 4000;
                int harga_banana = 5300;
                int harga_twister = 3800;
                int harga_populaire = 5500;
                int harga_shaky = 11000;
                int harga_cornetto_clasico = 5300;
                int harga_cornetto_disc = 12000;
                int harga_cornetto_oreo = 12000;
                int harga_cornetto_silver = 12000;
                int harga_magnum_clasic = 15000;
                int harga_magnum_almond = 15000;
                int harga_feast_wich = 9500;

                // masuk
                String mskk_paddlePop_coklat = editKelilingBinding.edtMasukPaddlecoklatKeliling.getText().toString();
                String mskk_paddlePop_rainbow = editKelilingBinding.edtMasukPaddlerainbowKeliling.getText().toString();
                String mskk_trico = editKelilingBinding.edtMasukTricoKeliling.getText().toString();
                String mskk_feast = editKelilingBinding.edtMasukFeastKeliling.getText().toString();
                String mskk_doraemon = editKelilingBinding.edtMasukDoraemonKeliling.getText().toString();
                String mskk_banana = editKelilingBinding.edtMasukBananaKeliling.getText().toString();
                String mskk_twister = editKelilingBinding.edtMasukTwisterKeliling.getText().toString();
                String mskk_magnum_clasic = editKelilingBinding.edtMasukMagnumclasicKeliling.getText().toString();
                String mskk_magnum_almond = editKelilingBinding.edtMasukMagnumalmondKeliling.getText().toString();
                String mskk_feast_wich = editKelilingBinding.edtMasukFeastwichKeliling.getText().toString();
                String mskk_populaire = editKelilingBinding.edtMasukPopulaireKeliling.getText().toString();
                String mskk_shaky = editKelilingBinding.edtMasukShakyKeliling.getText().toString();
                String mskk_cornetto_clasico = editKelilingBinding.edtMasukCornettoclasicoKeliling.getText().toString();
                String mskk_cornetto_disc = editKelilingBinding.edtMasukCornettodiscKeliling.getText().toString();
                String mskk_cornetto_oreo = editKelilingBinding.edtMasukCornettooreoKeliling.getText().toString();
                String mskk_cornetto_silver = editKelilingBinding.edtMasukCornettosilverKeliling.getText().toString();

                // sisa
                String sisk_paddlePop_coklat = editKelilingBinding.edtSisaPaddlecoklatKeliling.getText().toString();
                String sisk_paddlePop_rainbow = editKelilingBinding.edtSisaPaddlerainbowKeliling.getText().toString();
                String sisk_trico = editKelilingBinding.edtSisaTricoKeliling.getText().toString();
                String sisk_feast = editKelilingBinding.edtSisaFeastKeliling.getText().toString();
                String sisk_doraemon = editKelilingBinding.edtSisaDoraemonKeliling.getText().toString();
                String sisk_banana = editKelilingBinding.edtSisaBananaKeliling.getText().toString();
                String sisk_twister = editKelilingBinding.edtSisaTwisterKeliling.getText().toString();
                String sisk_magnum_clasic = editKelilingBinding.edtSisaMagnumclasicKeliling.getText().toString();
                String sisk_magnum_almond = editKelilingBinding.edtSisaMagnumalmondKeliling.getText().toString();
                String sisk_feast_wich = editKelilingBinding.edtSisaFeastwichKeliling.getText().toString();
                String sisk_populaire = editKelilingBinding.edtSisaPopulaireKeliling.getText().toString();
                String sisk_shaky = editKelilingBinding.edtSisaShakyKeliling.getText().toString();
                String sisk_cornetto_clasico = editKelilingBinding.edtSisaCornettoclasicoKeliling.getText().toString();
                String sisk_cornetto_disc = editKelilingBinding.edtSisaCornettodiscKeliling.getText().toString();
                String sisk_cornetto_oreo = editKelilingBinding.edtSisaCornettooreoKeliling.getText().toString();
                String sisk_cornetto_silver = editKelilingBinding.edtSisaCornettosilverKeliling.getText().toString();

                // terjual
                int terjual_paddle_coklat = Integer.parseInt(mskk_paddlePop_coklat) - Integer.parseInt(sisk_paddlePop_coklat);
                int terjual_paddle_rainbow = Integer.parseInt(mskk_paddlePop_rainbow) - Integer.parseInt(sisk_paddlePop_rainbow);
                int terjual_trico = Integer.parseInt(mskk_trico) - Integer.parseInt(sisk_trico);
                int terjual_feast = Integer.parseInt(mskk_feast) - Integer.parseInt(sisk_feast);
                int terjual_doraemon = Integer.parseInt(mskk_doraemon) - Integer.parseInt(sisk_doraemon);
                int terjual_banana = Integer.parseInt(mskk_banana) - Integer.parseInt(sisk_banana);
                int terjual_twister = Integer.parseInt(mskk_twister) - Integer.parseInt(sisk_twister);
                int terjual_magnum_clasic = Integer.parseInt(mskk_magnum_clasic) - Integer.parseInt(sisk_magnum_clasic);
                int terjual_magnum_almond = Integer.parseInt(mskk_magnum_almond) - Integer.parseInt(sisk_magnum_almond);
                int terjual_populaire = Integer.parseInt(mskk_populaire) - Integer.parseInt(sisk_populaire);
                int terjual_shaky = Integer.parseInt(mskk_shaky) - Integer.parseInt(sisk_shaky);
                int terjual_cornetto_clasico = Integer.parseInt(mskk_cornetto_clasico) - Integer.parseInt(sisk_cornetto_clasico);
                int terjual_cornetto_disc = Integer.parseInt(mskk_cornetto_disc) - Integer.parseInt(sisk_cornetto_disc);
                int terjual_cornetto_oreo = Integer.parseInt(mskk_cornetto_oreo) - Integer.parseInt(sisk_cornetto_oreo);
                int terjual_cornetto_silver = Integer.parseInt(mskk_cornetto_silver) - Integer.parseInt(sisk_cornetto_silver);
                int terjual_feast_wich = Integer.parseInt(mskk_feast_wich) - Integer.parseInt(sisk_feast_wich);

                // total
                int total_paddle_coklat = harga_paddle_coklat*terjual_paddle_coklat;
                int total_paddle_rainbow = harga_paddle_rainbow*terjual_paddle_rainbow;
                int total_trico = harga_trico*terjual_trico;
                int total_feast = harga_feast*terjual_feast;
                int total_doraemon = harga_doraemon*terjual_doraemon;
                int total_banana = harga_banana*terjual_banana;
                int total_twister = harga_twister*terjual_twister;
                int total_magnum_clasic = harga_magnum_clasic*terjual_magnum_clasic;
                int total_magnum_almond = harga_magnum_almond*terjual_magnum_almond;
                int total_populaire = harga_populaire*terjual_populaire;
                int total_shaky = harga_shaky*terjual_shaky;
                int total_cornetto_clasico = harga_cornetto_clasico*terjual_cornetto_clasico;
                int total_cornetto_disc = harga_cornetto_disc*terjual_cornetto_disc;
                int total_cornetto_oreo = harga_cornetto_oreo*terjual_cornetto_oreo;
                int total_cornetto_silver = harga_cornetto_silver*terjual_cornetto_silver;
                int total_feast_wich = harga_feast_wich*terjual_feast_wich;

                // grand total
                int grand_total = total_paddle_coklat+total_paddle_rainbow+total_trico+total_feast+total_doraemon+total_banana+total_twister+total_magnum_clasic+total_magnum_almond+total_populaire+total_shaky+total_cornetto_clasico+total_cornetto_disc+total_cornetto_oreo+total_cornetto_silver+total_feast_wich;

                // str total
                String str_ttl_paddle_coklat = String.valueOf(total_paddle_coklat);
                String str_ttl_paddle_rainbow = String.valueOf(total_paddle_rainbow);
                String str_ttl_trico = String.valueOf(total_trico);
                String str_ttl_feast = String.valueOf(total_feast);
                String str_ttl_doraemon = String.valueOf(total_doraemon);
                String str_ttl_banana = String.valueOf(total_banana);
                String str_ttl_twister = String.valueOf(total_twister);
                String str_ttl_magnum_clasic = String.valueOf(total_magnum_clasic);
                String str_ttl_magnum_almond = String.valueOf(total_magnum_almond);
                String str_ttl_populaire = String.valueOf(total_populaire);
                String str_ttl_shaky = String.valueOf(total_shaky);
                String str_ttl_cornetto_clasico = String.valueOf(total_cornetto_clasico);
                String str_ttl_cornetto_disc = String.valueOf(total_cornetto_disc);
                String str_ttl_cornetto_oreo = String.valueOf(total_cornetto_oreo);
                String str_ttl_cornetto_silver = String.valueOf(total_cornetto_silver);
                String str_ttl_feast_wich = String.valueOf(total_feast_wich);

                // str terjual
                String str_tjl_paddle_coklat = String.valueOf(terjual_paddle_coklat);
                String str_tjl_paddle_rainbow = String.valueOf(terjual_paddle_rainbow);
                String str_tjl_trico = String.valueOf(terjual_trico);
                String str_tjl_feast = String.valueOf(terjual_feast);
                String str_tjl_doraemon = String.valueOf(terjual_doraemon);
                String str_tjl_banana = String.valueOf(terjual_banana);
                String str_tjl_twister = String.valueOf(terjual_twister);
                String str_tjl_magnum_clasic = String.valueOf(terjual_magnum_clasic);
                String str_tjl_magnum_almond = String.valueOf(terjual_magnum_almond);
                String str_tjl_populaire = String.valueOf(terjual_populaire);
                String str_tjl_shaky = String.valueOf(terjual_shaky);
                String str_tjl_cornetto_clasico = String.valueOf(terjual_cornetto_clasico);
                String str_tjl_cornetto_disc = String.valueOf(terjual_cornetto_disc);
                String str_tjl_cornetto_oreo = String.valueOf(terjual_cornetto_oreo);
                String str_tjl_cornetto_silver = String.valueOf(terjual_cornetto_silver);
                String str_tjl_feast_wich = String.valueOf(terjual_feast_wich);

                // str grand
                String str_grand_total = String.valueOf(grand_total);

                String number = "999";

                //ModelKeliling mkEdit = new ModelKeliling(mskk_paddlePop_coklat,mskk_paddlePop_rainbow,mskk_trico,mskk_shaky,mskk_cornetto_clasico,mskk_cornetto_disc,mskk_cornetto_oreo,mskk_cornetto_silver,mskk_magnum_clasic,mskk_magnum_almond,mskk_populaire,mskk_feast,mskk_doraemon,mskk_banana,mskk_twister,mskk_feast_wich,sisk_paddlePop_coklat,sisk_paddlePop_rainbow,sisk_trico,sisk_shaky,sisk_cornetto_clasico,sisk_cornetto_disc,sisk_cornetto_oreo,sisk_cornetto_silver,sisk_magnum_clasic,sisk_magnum_almond,sisk_populaire,sisk_feast,sisk_doraemon,sisk_banana,sisk_twister,sisk_feast_wich,date,keyId);
                ModelKeliling mkEdit = new ModelKeliling(number,str_grand_total,str_tjl_paddle_coklat,str_tjl_paddle_rainbow,str_tjl_trico,str_tjl_shaky,str_tjl_cornetto_clasico,str_tjl_cornetto_disc,str_tjl_cornetto_oreo,str_tjl_cornetto_silver,str_tjl_magnum_clasic,str_tjl_magnum_almond,str_tjl_populaire,str_tjl_feast,str_tjl_doraemon,str_tjl_banana,str_tjl_twister,str_tjl_feast_wich,str_ttl_paddle_coklat,str_ttl_paddle_rainbow,str_ttl_trico,str_ttl_shaky,str_ttl_cornetto_clasico,str_ttl_cornetto_disc,str_ttl_cornetto_oreo,str_ttl_cornetto_silver,str_ttl_magnum_clasic,str_ttl_magnum_almond,str_ttl_populaire,str_ttl_feast,str_ttl_doraemon,str_ttl_banana,str_ttl_twister,str_ttl_feast_wich,mskk_paddlePop_coklat,mskk_paddlePop_rainbow,mskk_trico,mskk_shaky,mskk_cornetto_clasico,mskk_cornetto_disc,mskk_cornetto_oreo,mskk_cornetto_silver,mskk_magnum_clasic,mskk_magnum_almond,mskk_populaire,mskk_feast,mskk_doraemon,mskk_banana,mskk_twister,mskk_feast_wich,sisk_paddlePop_coklat,sisk_paddlePop_rainbow,sisk_trico,sisk_shaky,sisk_cornetto_clasico,sisk_cornetto_disc,sisk_cornetto_oreo,sisk_cornetto_silver,sisk_magnum_clasic,sisk_magnum_almond,sisk_populaire,sisk_feast,sisk_doraemon,sisk_banana,sisk_twister,sisk_feast_wich,date,keyId);
                keliling.UpdateKeliling(mkEdit);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Edit Berhasil", "Edit Berhasil");
                        dialog.dismiss();
                    }
                },200);

            }
        });

        editKelilingBinding.btnStockKeliling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference referenceStock = FirebaseDatabase.getInstance().getReference("DataStock").child("Data");
                ModelStock modelStock = new ModelStock();

                // masuk
                String mskk_paddlePop_coklat = editKelilingBinding.edtMasukPaddlecoklatKeliling.getText().toString();
                String mskk_paddlePop_rainbow = editKelilingBinding.edtMasukPaddlerainbowKeliling.getText().toString();
                String mskk_trico = editKelilingBinding.edtMasukTricoKeliling.getText().toString();
                String mskk_feast = editKelilingBinding.edtMasukFeastKeliling.getText().toString();
                String mskk_doraemon = editKelilingBinding.edtMasukDoraemonKeliling.getText().toString();
                String mskk_banana = editKelilingBinding.edtMasukBananaKeliling.getText().toString();
                String mskk_twister = editKelilingBinding.edtMasukTwisterKeliling.getText().toString();
                String mskk_magnum_clasic = editKelilingBinding.edtMasukMagnumclasicKeliling.getText().toString();
                String mskk_magnum_almond = editKelilingBinding.edtMasukMagnumalmondKeliling.getText().toString();
                String mskk_feast_wich = editKelilingBinding.edtMasukFeastwichKeliling.getText().toString();
                String mskk_populaire = editKelilingBinding.edtMasukPopulaireKeliling.getText().toString();
                String mskk_shaky = editKelilingBinding.edtMasukShakyKeliling.getText().toString();
                String mskk_cornetto_clasico = editKelilingBinding.edtMasukCornettoclasicoKeliling.getText().toString();
                String mskk_cornetto_disc = editKelilingBinding.edtMasukCornettodiscKeliling.getText().toString();
                String mskk_cornetto_oreo = editKelilingBinding.edtMasukCornettooreoKeliling.getText().toString();
                String mskk_cornetto_silver = editKelilingBinding.edtMasukCornettosilverKeliling.getText().toString();

                // sisa
                String sisk_paddlePop_coklat = editKelilingBinding.edtSisaPaddlecoklatKeliling.getText().toString();
                String sisk_paddlePop_rainbow = editKelilingBinding.edtSisaPaddlerainbowKeliling.getText().toString();
                String sisk_trico = editKelilingBinding.edtSisaTricoKeliling.getText().toString();
                String sisk_feast = editKelilingBinding.edtSisaFeastKeliling.getText().toString();
                String sisk_doraemon = editKelilingBinding.edtSisaDoraemonKeliling.getText().toString();
                String sisk_banana = editKelilingBinding.edtSisaBananaKeliling.getText().toString();
                String sisk_twister = editKelilingBinding.edtSisaTwisterKeliling.getText().toString();
                String sisk_magnum_clasic = editKelilingBinding.edtSisaMagnumclasicKeliling.getText().toString();
                String sisk_magnum_almond = editKelilingBinding.edtSisaMagnumalmondKeliling.getText().toString();
                String sisk_feast_wich = editKelilingBinding.edtSisaFeastwichKeliling.getText().toString();
                String sisk_populaire = editKelilingBinding.edtSisaPopulaireKeliling.getText().toString();
                String sisk_shaky = editKelilingBinding.edtSisaShakyKeliling.getText().toString();
                String sisk_cornetto_clasico = editKelilingBinding.edtSisaCornettoclasicoKeliling.getText().toString();
                String sisk_cornetto_disc = editKelilingBinding.edtSisaCornettodiscKeliling.getText().toString();
                String sisk_cornetto_oreo = editKelilingBinding.edtSisaCornettooreoKeliling.getText().toString();
                String sisk_cornetto_silver = editKelilingBinding.edtSisaCornettosilverKeliling.getText().toString();

                // terjual
                int terjual_paddle_coklat = Integer.parseInt(mskk_paddlePop_coklat) - Integer.parseInt(sisk_paddlePop_coklat);
                int terjual_paddle_rainbow = Integer.parseInt(mskk_paddlePop_rainbow) - Integer.parseInt(sisk_paddlePop_rainbow);
                int terjual_trico = Integer.parseInt(mskk_trico) - Integer.parseInt(sisk_trico);
                int terjual_feast = Integer.parseInt(mskk_feast) - Integer.parseInt(sisk_feast);
                int terjual_doraemon = Integer.parseInt(mskk_doraemon) - Integer.parseInt(sisk_doraemon);
                int terjual_banana = Integer.parseInt(mskk_banana) - Integer.parseInt(sisk_banana);
                int terjual_twister = Integer.parseInt(mskk_twister) - Integer.parseInt(sisk_twister);
                int terjual_magnum_clasic = Integer.parseInt(mskk_magnum_clasic) - Integer.parseInt(sisk_magnum_clasic);
                int terjual_magnum_almond = Integer.parseInt(mskk_magnum_almond) - Integer.parseInt(sisk_magnum_almond);
                int terjual_populaire = Integer.parseInt(mskk_populaire) - Integer.parseInt(sisk_populaire);
                int terjual_shaky = Integer.parseInt(mskk_shaky) - Integer.parseInt(sisk_shaky);
                int terjual_cornetto_clasico = Integer.parseInt(mskk_cornetto_clasico) - Integer.parseInt(sisk_cornetto_clasico);
                int terjual_cornetto_disc = Integer.parseInt(mskk_cornetto_disc) - Integer.parseInt(sisk_cornetto_disc);
                int terjual_cornetto_oreo = Integer.parseInt(mskk_cornetto_oreo) - Integer.parseInt(sisk_cornetto_oreo);
                int terjual_cornetto_silver = Integer.parseInt(mskk_cornetto_silver) - Integer.parseInt(sisk_cornetto_silver);
                int terjual_feast_wich = Integer.parseInt(mskk_feast_wich) - Integer.parseInt(sisk_feast_wich);

                referenceStock.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        // parse from model stock

                        int stock_paddle_coklat = Integer.parseInt(snapshot.child("stock_paddlepop_coklat").getValue().toString());
                        int stock_paddle_rainbow = Integer.parseInt(snapshot.child("stock_paddlepop_rainbow").getValue().toString());
                        int stock_trico = Integer.parseInt(snapshot.child("stock_paddlepop_trico").getValue().toString());
                        int stock_feast = Integer.parseInt(snapshot.child("stock_feast").getValue().toString());
                        int stock_doraemon = Integer.parseInt(snapshot.child("stock_doraemon").getValue().toString());
                        int stock_banana = Integer.parseInt(snapshot.child("stock_banana").getValue().toString());
                        int stock_twister = Integer.parseInt(snapshot.child("stock_twister").getValue().toString());
                        int stock_magnum_clasic = Integer.parseInt(snapshot.child("stock_magnum_clasic").getValue().toString());
                        int stock_magnum_almond = Integer.parseInt(snapshot.child("stock_magnum_almond").getValue().toString());
                        int stock_populaire = Integer.parseInt(snapshot.child("stock_populaire").getValue().toString());
                        int stock_shaky = Integer.parseInt(snapshot.child("stock_shaky").getValue().toString());
                        int stock_cornetto_clasico = Integer.parseInt(snapshot.child("stock_cornetto_clasico").getValue().toString());
                        int stock_cornetto_disc = Integer.parseInt(snapshot.child("stock_cornetto_disc").getValue().toString());
                        int stock_cornetto_oreo = Integer.parseInt(snapshot.child("stock_cornetto_oreo").getValue().toString());
                        int stock_cornetto_silver = Integer.parseInt(snapshot.child("stock_cornetto_silver").getValue().toString());
                        int stock_feast_wich = Integer.parseInt(snapshot.child("stock_feast_wich").getValue().toString());

                        // stock reduction
                        int redct_paddle_coklat = stock_paddle_coklat - terjual_paddle_coklat;
                        int redct_paddle_rainbow = stock_paddle_rainbow - terjual_paddle_rainbow;
                        int redct_trico = stock_trico - terjual_trico;
                        int redct_feast = stock_feast - terjual_feast;
                        int redct_doraemon = stock_doraemon - terjual_doraemon;
                        int redct_banana = stock_banana - terjual_banana;
                        int redct_twister = stock_twister - terjual_twister;
                        int redct_magnum_clasic = stock_magnum_clasic - terjual_magnum_clasic;
                        int redct_magnum_almond = stock_magnum_almond - terjual_magnum_almond;
                        int redct_populaire = stock_populaire - terjual_populaire;
                        int redct_shaky = stock_shaky - terjual_shaky;
                        int redct_cornetto_clasico = stock_cornetto_clasico - terjual_cornetto_clasico;
                        int redct_cornetto_disc = stock_cornetto_disc - terjual_cornetto_disc;
                        int redct_cornetto_oreo = stock_cornetto_oreo - terjual_cornetto_oreo;
                        int redct_cornetto_silver = stock_cornetto_silver - terjual_cornetto_silver;
                        int redct_feast_wich = stock_feast_wich - terjual_feast_wich;

                        // perse reduction stock
                        String paddleCoklat = String.valueOf(redct_paddle_coklat);
                        String paddleRainbow= String.valueOf(redct_paddle_rainbow);
                        String trico= String.valueOf(redct_trico);
                        String feast= String.valueOf(redct_feast);
                        String doraemon= String.valueOf(redct_doraemon);
                        String banana= String.valueOf(redct_banana);
                        String twister= String.valueOf(redct_twister);
                        String magnumClasic= String.valueOf(redct_magnum_clasic);
                        String magnumAlmond= String.valueOf(redct_magnum_almond);
                        String shaky= String.valueOf(redct_shaky);
                        String populaire= String.valueOf(redct_populaire);
                        String cornettoClasicco= String.valueOf(redct_cornetto_clasico);
                        String cornettoDisc= String.valueOf(redct_cornetto_disc);
                        String cornettoSilver= String.valueOf(redct_cornetto_silver);
                        String cornettoOreo= String.valueOf(redct_cornetto_oreo);
                        String feastWich= String.valueOf(redct_feast_wich);


                        // set value model stock

                            modelStock.setStock_paddlepop_coklat(paddleCoklat);
                            modelStock.setStock_paddlepop_rainbow(paddleRainbow);
                            modelStock.setStock_paddlepop_trico(trico);
                            modelStock.setStock_feast(feast);
                            modelStock.setStock_doraemon(doraemon);
                            modelStock.setStock_banana(banana);
                            modelStock.setStock_twister(twister);
                            modelStock.setStock_magnum_clasic(magnumClasic);
                            modelStock.setStock_magnum_almond(magnumAlmond);
                            modelStock.setStock_shaky(shaky);
                            modelStock.setStock_populaire(populaire);
                            modelStock.setStock_cornetto_clasico(cornettoClasicco);
                            modelStock.setStock_cornetto_disc(cornettoDisc);
                            modelStock.setStock_cornetto_silver(cornettoSilver);
                            modelStock.setStock_cornetto_oreo(cornettoOreo);
                            modelStock.setStock_feast_wich(feastWich);




                        // push value
                        referenceStock.setValue(modelStock);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        });


    }


    private void seeKeliling(ModelKeliling mk, SeeKelilingBinding seeKelilingBinding){
        Dialog dialog = new Dialog(context);
        View view = seeKelilingBinding.getRoot();
        dialog.setContentView(view);
        dialog.show();



        // set total
        seeKelilingBinding.totalPaddlepopCoklat.setText("Total: "+mk.getTotal_paddlepop_coklat());
        seeKelilingBinding.totalPaddlepopRainbow.setText("Total: "+mk.getTotal_paddlepop_rainbow());
        seeKelilingBinding.totalTrico.setText("Total: "+mk.getTotal_paddlepop_trico());
        seeKelilingBinding.totalFeast.setText("Total: "+mk.getTotal_feast());
        seeKelilingBinding.totalDoraemon.setText("Total: "+mk.getTotal_doraemon());
        seeKelilingBinding.totalBanana.setText("Total: "+mk.getTotal_banana());
        seeKelilingBinding.totalTwister.setText("Total: "+mk.getTotal_twister());
        seeKelilingBinding.totalShaky.setText("Total: "+mk.getTotal_shaky());
        seeKelilingBinding.totalPopulaire.setText("Total: "+mk.getTotal_populaire());
        seeKelilingBinding.totalCornettoClasico.setText("Total: "+mk.getTotal_cornetto_clasico());
        seeKelilingBinding.totalCornettoDisc.setText("Total: "+mk.getTotal_cornetto_disc());
        seeKelilingBinding.totalCornettoOreo.setText("Total: "+mk.getTotal_cornetto_oreo());
        seeKelilingBinding.totalCornettoSilver.setText("Total: "+mk.getTotal_cornetto_silver());
        seeKelilingBinding.totalMagnumAlmond.setText("Total: "+mk.getTotal_magnum_almond());
        seeKelilingBinding.totalMagnumClasic.setText("Total: "+mk.getTotal_magnum_clasic());
        seeKelilingBinding.totalFeastWich.setText("Total: "+mk.getTotal_feast_wich());

        // set terjual
        seeKelilingBinding.terjualPaddlepopCoklat.setText("Terjual: "+mk.getTerjual_paddlepop_coklat());
        seeKelilingBinding.terjualPaddlepopRainbow.setText("Terjual: "+mk.getTerjual_paddlepop_rainbow());
        seeKelilingBinding.terjualTrico.setText("Terjual: "+mk.getTerjual_paddlepop_trico());
        seeKelilingBinding.terjualFeast.setText("Terjual: "+mk.getTerjual_feast());
        seeKelilingBinding.terjualDoraemon.setText("Terjual: "+mk.getTerjual_doraemon());
        seeKelilingBinding.terjualBanana.setText("Terjual: "+mk.getTerjual_banana());
        seeKelilingBinding.terjualTwister.setText("Terjual: "+mk.getTerjual_twister());
        seeKelilingBinding.terjualShaky.setText("Terjual: "+mk.getTerjual_shaky());
        seeKelilingBinding.terjualPopulaire.setText("Terjual: "+mk.getTerjual_populaire());
        seeKelilingBinding.terjualCornettoClasico.setText("Terjual: "+mk.getTerjual_cornetto_clasico());
        seeKelilingBinding.terjualCornettoDisc.setText("Terjual: "+mk.getTerjual_cornetto_disc());
        seeKelilingBinding.terjualCornettoOreo.setText("Terjual: "+mk.getTerjual_cornetto_oreo());
        seeKelilingBinding.terjualCornettoSilver.setText("Terjual: "+mk.getTerjual_cornetto_silver());
        seeKelilingBinding.terjualMagnumAlmond.setText("Terjual: "+mk.getTerjual_magnum_almond());
        seeKelilingBinding.terjualMagnumClasic.setText("Terjual: "+mk.getTerjual_magnum_clasic());
        seeKelilingBinding.terjualFeastWich.setText("Terjual: "+mk.getTerjual_feast_wich());

        // set grand


        // masuk
        seeKelilingBinding.masukSeePaddlepopCoklat.setText("Masuk: "+mk.getMasuk_paddlepop_coklat());
        seeKelilingBinding.masukSeePaddlepopRainbow.setText("Masuk: "+mk.getMasuk_paddlepop_rainbow());
        seeKelilingBinding.masukSeeTrico.setText("Masuk: "+mk.getMasuk_paddlepop_trico());
        seeKelilingBinding.masukSeeFeast.setText("Masuk: "+mk.getMasuk_feast());
        seeKelilingBinding.masukSeeDoraemon.setText("Masuk: "+mk.getMasuk_doraemon());
        seeKelilingBinding.masukSeeBanana.setText("Masuk: "+mk.getMasuk_banana());
        seeKelilingBinding.masukSeeTwister.setText("Masuk: "+mk.getMasuk_twister());
        seeKelilingBinding.masukSeeMagnumClasic.setText("Masuk: "+mk.getMasuk_magnum_clasic());
        seeKelilingBinding.masukSeeMagnumAlmond.setText("Masuk: "+mk.getMasuk_magnum_almond());
        seeKelilingBinding.masukSeeFeastWich.setText("Masuk: "+mk.getMasuk_feast_wich());
        seeKelilingBinding.masukSeePopulaire.setText("Masuk: "+mk.getMasuk_populaire());
        seeKelilingBinding.masukSeeShaky.setText("Masuk: "+mk.getMasuk_shaky());
        seeKelilingBinding.masukSeeCornettoClasico.setText("Masuk: "+mk.getMasuk_cornetto_clasico());
        seeKelilingBinding.masukSeeCornettoDisc.setText("Masuk: "+mk.getMasuk_cornetto_disc());
        seeKelilingBinding.masukSeeCornettoOreo.setText("Masuk: "+mk.getMasuk_cornetto_oreo());
        seeKelilingBinding.masukSeeCornettoSilver.setText("Masuk: "+mk.getMasuk_cornetto_silver());

        // sisa
        seeKelilingBinding.sisaSeePaddlepopCoklat.setText("Sisa: "+mk.getSisa_paddlepop_coklat());
        seeKelilingBinding.sisaSeePaddlepopRainbow.setText("Sisa: "+mk.getSisa_paddlepop_rainbow());
        seeKelilingBinding.sisaSeeTrico.setText("Sisa: "+mk.getSisa_paddlepop_trico());
        seeKelilingBinding.sisaSeeFeast.setText("Sisa: "+mk.getSisa_feast());
        seeKelilingBinding.sisaSeeDoraemon.setText("Sisa: "+mk.getSisa_doraemon());
        seeKelilingBinding.sisaSeeBanana.setText("Sisa: "+mk.getSisa_banana());
        seeKelilingBinding.sisaSeeTwister.setText("Sisa: "+mk.getSisa_twister());
        seeKelilingBinding.sisaSeeMagnumClasic.setText("Sisa: "+mk.getSisa_magnum_clasic());
        seeKelilingBinding.sisaSeeMagnumAlmond.setText("Sisa: "+mk.getSisa_magnum_almond());
        seeKelilingBinding.sisaSeeFeastWich.setText("Sisa: "+mk.getSisa_feast_wich());
        seeKelilingBinding.sisaSeePopulaire.setText("Sisa: "+mk.getSisa_populaire());
        seeKelilingBinding.sisaSeeShaky.setText("Sisa: "+mk.getSisa_shaky());
        seeKelilingBinding.sisaSeeCornettoClasico.setText("Sisa: "+mk.getSisa_cornetto_clasico());
        seeKelilingBinding.sisaSeeCornettoDisc.setText("Sisa: "+mk.getSisa_cornetto_disc());
        seeKelilingBinding.sisaSeeCornettoOreo.setText("Sisa: "+mk.getSisa_cornetto_oreo());
        seeKelilingBinding.sisaSeeCornettoSilver.setText("Sisa: "+mk.getSisa_cornetto_silver());

        String grnd = mk.getGrand_total();
        seeKelilingBinding.seeGrandTotall.setText("Grand Total: "+grnd);

        keyId = mk.getUserId();



    }


}
