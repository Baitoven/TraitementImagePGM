/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.traitementpgm;

import java.io.*;
import static java.lang.Math.*;
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
    
    
    public static void main(String[] args) throws IOException {
        ImagePGM im = new ImagePGM("image_test.pgm");
        int[][] val = new int[100][100];
        im.ecriture(val);
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getNivGrisMax() {
        return nivGrisMax;
    }

    public void setNivGrisMax(int nivGrisMax) {
        this.nivGrisMax = nivGrisMax;
    }
    
    
    
    
    
    
    

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

    public void histo() throws IOException {
        //creation du tableau
        ImagePGM im = new ImagePGM("histo.pgm");
        im.largeur = 256;
        im.nivGrisMax = 255;
        int[] histoTab = new int[im.largeur];
       
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
        im.hauteur = maxHistoTab;
        im.image = new int[im.largeur][im.hauteur];
        
        for(int k = 0; k < 256; k++ ){
            for (int l = 0; l < histoTab[k]; l++){
                im.image[k][255-l] = 255;
            }
        }
        
        
    }
    
    public void diff() throws IOException {
        // récupération des 2 images
        ImagePGM im1 = new ImagePGM("image1.pgm");
        ImagePGM im2 = new ImagePGM("image2.pgm");
        // on suppose que les 2 images sont de la même taille
        ImagePGM imdiff = new ImagePGM("diff12.pgm");
        for (int i = 0; i < im1.image.length; i++) {
            for (int j = 0; j < im1.image[i].length; j++) {
                imdiff.image[i][j] = abs(im1.image[i][j] - im2.image[i][j]);
            }
        }
    }
    
    public void seuil(int val_seuil) throws IOException {
        // récupération des 2 images
        ImagePGM im1 = new ImagePGM("image1.pgm");
        ImagePGM imseuil = new ImagePGM("immseuil.pgm");
        for (int i = 0; i < im1.image.length; i++) {
            for (int j = 0; j < im1.image[i].length; j++) {
               imseuil.image[i][j] = max(val_seuil,im1.image[i][j]);
            }
        }
    }
}
