/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.traitementpgm;

import java.io.*;
import java.util.StringTokenizer;

/**
 *
 * @author Julien
 */
public class ImagePGM {

    private String filename;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private String commentaire;
    private int largeur;
    private int hauteur;
    private int nivGrisMax;
    private int[][] image;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    //Constructeur à partir d'un fichier

    public ImagePGM(String file) throws FileNotFoundException, IOException{
        this.filename = file;
        try {
            String ligne;
            String delimiteurs = " .;,";
            bufferedReader = new BufferedReader(new FileReader(file));
            ligne = bufferedReader.readLine();
            this.commentaire = ligne;
            ligne = bufferedReader.readLine();
            StringTokenizer tokenizer1 = new StringTokenizer(ligne, delimiteurs);
            this.largeur = Integer.parseInt(tokenizer1.nextToken());
            this.hauteur = Integer.parseInt(tokenizer1.nextToken());
            ligne = bufferedReader.readLine();
            StringTokenizer tokenizer2 = new StringTokenizer(ligne, delimiteurs);
            this.nivGrisMax = Integer.parseInt(tokenizer2.nextToken());
            for (int i=0; i<largeur; i++){
                for(int j =0; j<hauteur; j++){
                    image[i][j] = Integer.parseInt(tokenizer1.nextToken());
                }
            }
        } catch (Exception e1) {
                System.out.println("coucou");
                    }
    }

    public void ecriture(int[][] image) {
        this.bufferedWriter = null;

        try {
            // Creation du BufferedWriter
            bufferedWriter = new BufferedWriter(new FileWriter(filename));
            // On ecrit dans le fichier
            bufferedWriter.write("P2");
            bufferedWriter.newLine();
            bufferedWriter.write("#ceci est un commentaire");
            bufferedWriter.newLine();
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[i].length; j++) {
                    bufferedWriter.write(Integer.toString(image[i][j]) + " ");
                }
                bufferedWriter.newLine();
            }
            // on attrape l'exception si on a pas pu creer le fichier
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } // on attrape l'exception si il y a un probleme lors de l'ecriture dans le fichier
        catch (IOException ex) {
            ex.printStackTrace();
        } // on ferme le fichier
        finally {
            try {
                if (bufferedWriter != null) {
                    // je force l'écriture dans le fichier
                    bufferedWriter.flush();
                    // puis je le ferme
                    bufferedWriter.close();
                }
            } // on attrape l'exception potentielle 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public int[][] histo() {
        
        //creation du tableau
        int[] histoTab = new int[this.nivGrisMax];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                histoTab[image[i][j]]++;
            }
        }
        //recherche du max de ce tableau
        int maxHistoTab = -1;
        for (int k = 0; k< histoTab.length; k++) {
            maxHistoTab = Integer.max(maxHistoTab,histoTab[k]);
        }
        int [][] histoValues = new int[256][maxHistoTab];
        //A FINIR
        System.out.println("coucou");
        return histoValues;
    }
}
