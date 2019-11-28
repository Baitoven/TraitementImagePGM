/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.traitementpgm;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Julien
 */
public class ImagePGM {

    private String filename;
    private BufferedWriter bufferedWriter;

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

    /**
     *
     */
    public void lecture() {

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
}
