package com.cobacoba;

import com.sun.tools.javac.Main;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Scanner;

public class LatihanLatihan {
    public static void main(String[] args) {
        String[][] DataProduk = new String[0][0];
        String[][] NewData = MainProgram(DataProduk);



    }private static String[][] MainProgram(String[][] DataProduk){
        Scanner InputData = new Scanner(System.in);
        String[][] Pembandingan = new String[0][0];
        System.out.println("\n=============== Tarakan Shop ===============");
        System.out.println("\n======= Option =======");
        System.out.println("1 -> Input Data Produk\n2 -> Cetak Data Produk\n3 -> Beli Produk\n4 -> Close");
        System.out.print("Masukan Option: ");
        int InputOption = InputData.nextInt();
        switch (InputOption){
            case 1:
                System.out.println("---> Input Data Produk");
                System.out.print("Masukan Jumlah Jenis Produk Yang Akan Di Input: ");
                int Baris = InputData.nextInt();
                DataProduk = new String[Baris][5];
                String[][] InputDataProduk = MenuInputData(DataProduk, Baris);
                System.out.print("Tekan Y Untuk Kembali Ke Option: ");
                String ToOption = InputData.next();
                String UpperInput = ToOption.toUpperCase();
                if(UpperInput.equals("Y")){
                    String[][] NewData = MainProgram(DataProduk);
                }
                break;
            case 2:
                if(Arrays.equals(DataProduk,Pembandingan)){
                    System.out.println("Maaf data Masih Kosong\n");
                    System.out.println("Silahkan Pilih Option Input Data Untuk Input Data Produk:)");
                    System.out.print("Silahkan Tekan Y untuk Melanjutkan Dan T Untuk Keluar: ");
                    String Keputusan = InputData.next();
                    String UpperInput_1 = Keputusan.toUpperCase();
                    switch (UpperInput_1){
                        case "Y":
                            String[][] NewData = MainProgram(DataProduk);
                            break;
                        case "T":
                            break;
                    }
                }else{
                    System.out.println("---> Cetak Data Produk");
                    MenuPrintData(DataProduk);
                    System.out.print("Tekan Y Untuk Kembali Ke Option: ");
                    String ToOption_1 = InputData.next();
                    String UpperInput_2 = ToOption_1.toUpperCase();
                    if(UpperInput_2.equals("Y")){
                        String[][] NewData = MainProgram(DataProduk);
                    }
                }break;
            case 3:
                if(Arrays.equals(DataProduk,Pembandingan)){
                    System.out.println("Maaf Data Masih Kosong");
                    System.out.println("Silahkan Pilih Option Input Data Untuk Input Data Produk:)");
                    System.out.print("Silahkan Tekan Y untuk Melanjutkan Dan T Untuk Keluar: ");
                    String Keputusan = InputData.next();
                    String UpperInput_3= Keputusan.toUpperCase();
                    switch (UpperInput_3){
                        case "Y":
                            String[][] NewData = MainProgram(DataProduk);
                            break;
                        case "T":
                            break;
                    }
                }else{
                    System.out.println("---> Beli Produk");
                    MenuBeliProduk(DataProduk);
                    System.out.print("Tekan Y Untuk Kembali Ke Option: ");
                    String ToOption_2 = InputData.next();
                    String UpperInput_3 = ToOption_2.toUpperCase();
                    if(UpperInput_3.equals("Y")){
                        String[][] NewData = MainProgram(DataProduk);
                    }
                }break;
            case 4:
                break;
        }return DataProduk;
    }

    private static String[][] MenuInputData(String[][] DataProduk, int JumlahBaris) {
        Scanner InputDataProduk = new Scanner(System.in);
        String[][] NewData = new String[JumlahBaris][5];
        NewData = DataProduk;
        int Baris = NewData.length;
        int Kolom = NewData[0].length;
        for (int i = 0; i < Baris; i++) {
            System.out.println("---> DATA PRODUK");
            System.out.print("Masukan Nama Produk: ");
            NewData[i][0] = "Nama Produk: " + InputDataProduk.next();
            System.out.print("Masukan Kode Produk: ");
            NewData[i][1] = "Kode:" + InputDataProduk.next();
            System.out.print("Masukan Stok Produk: ");
            NewData[i][2] = "Stok:" + InputDataProduk.next();
            System.out.print("Masukan Harga Produk: ");
            NewData[i][3] = "Harga" + InputDataProduk.next();
            System.out.print("Masukan Foto Produk: ");
            NewData[i][4] = InputDataProduk.next() + ".JPG";
        }
        return DataProduk;
    }

    private static void MenuPrintData(String[][] DataProduk) {
        System.out.println("---> MENAMPILKAN DATA PRODUK");
        Scanner InputData = new Scanner(System.in);
        System.out.print("Masukan Kode Produk: ");
        String KodeProduk = InputData.next();
        for (int k = 0; k < DataProduk.length; k++) {
            if(KodeProduk.equals(DataProduk[k][1]))
                for (int i = 0; i < DataProduk.length; i++) {
                        System.out.printf("|%s| ", DataProduk[k][i]);
                }
            System.out.println("");
        }
    }

    private static void MenuBeliProduk(String[][] DataProduk) {
        Scanner InputData = new Scanner(System.in);
        System.out.print("Masukan Kode Produk: ");
        String KodeProduk = InputData.next();
        System.out.print("Masukan Jumlah Produk: ");
        int JumlahProduk = InputData.nextInt();
        for (int i = 0; i < DataProduk.length; i++) {
            if (KodeProduk.equals(DataProduk[i][1])) {
                float CastingStringHarga = Float.parseFloat(DataProduk[i][3]);
                float TotalHarga = CastingStringHarga * JumlahProduk;
                System.out.printf("|Nama Produk: %s| |Jumlah: %d| |Harga: %f| |Total Harga: %f|");
            }
        }
    }
}



