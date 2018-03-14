/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparison;

import DatabaseOperating.DatabaseReader;
import FingerPrint.FingerPrint;
import FingerPrint.WordsFilter;
import Reader.DocumentHelper;
import Reader.MainReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import java.util.TreeMap;
import util.MapSorter;
import util.OutPuter;
import util.SimilarityCalculation;

/**
 *
 * @author allwi
 */
public class DatabaseAccess_FeatureQuarter {

    public static void singleDatabaseSearch(String input, String database, String tempTxt) throws IOException {

        MainReader.articleReader(input, tempTxt);
        FingerPrint tempWordTrie = MainReader.constructReader(tempTxt);

        int tempTopWord = tempWordTrie.getTopWords() / 4;
        int tempMinAppr = tempWordTrie.getMinApprs() * 4;
        Map tempTopMap = tempWordTrie.topWordsMap(tempTopWord);
        TreeMap tempCond = WordsFilter.minApprFilter(tempMinAppr, tempTopMap);

        String[] all_files_database = DocumentHelper.getFileName(database);

        Map<String, Double> tempMap = new TreeMap<>();

        for (String file_file : all_files_database) {

            String fileFullPath = database + file_file;

            TreeMap dbMap = DatabaseReader.featureReader(fileFullPath);
            TreeMap dbCond = SimilarityCalculation.extraVector(tempCond, dbMap);

            int[] tempv = SimilarityCalculation.createVector(tempCond);
            int[] dbv = SimilarityCalculation.createVector(dbCond);

            double sim = SimilarityCalculation.cosineAlgorithm(tempv, dbv);

            if (Double.isNaN(sim)) {
                sim = 0;
            }

            tempMap.put(file_file, sim);
        }

        System.out.println(tempMap);
        Map<String, Double> simMap = MapSorter.sortByValuesDesc(tempMap);
        Map.Entry<String, Double> first = simMap.entrySet().iterator().next();
        String key = first.getKey();
        double value = first.getValue();

        System.out.println(key + " " + value + " ");

    }

    public static void databaseSearch(String input, String database, String searchInfo, String tempTxt) throws IOException {

        String[] all_files_input = DocumentHelper.getFileName(input);
        String[] all_files_database = DocumentHelper.getFileName(database);

        Map<String, Double> tempMap = new TreeMap<>();

        for (String file_input : all_files_input) {

            long total_Start = System.nanoTime();

            long read_Start = System.nanoTime();
            MainReader.articleReader(input + file_input, tempTxt);
            long read_End = System.nanoTime();

            long create_Start = System.nanoTime();
            FingerPrint tempWordTrie = MainReader.constructReader(tempTxt);
            int tempTopWord = tempWordTrie.getTopWords() / 4;
            int tempMinAppr = tempWordTrie.getMinApprs() * 4;
            Map tempTopMap = tempWordTrie.topWordsMap(tempTopWord);
            TreeMap tempCond = WordsFilter.minApprFilter(tempMinAppr, tempTopMap);
            long create_End = System.nanoTime();

            long compare_Start = System.nanoTime();

            long s_rc = 0, s_vr = 0, s_cs = 0, s_pt = 0;

            for (String file_database : all_files_database) {

                long s_rc_s = System.nanoTime();
                String fileFullPath = database + file_database;
                TreeMap dbMap = DatabaseReader.featureReader(fileFullPath);
                long s_rc_e = System.nanoTime();

                long s_vr_s = System.nanoTime();
                TreeMap dbCond = SimilarityCalculation.extraVector(tempCond, dbMap);
                int[] tempv = SimilarityCalculation.createVector(tempCond);
                int[] dbv = SimilarityCalculation.createVector(dbCond);
                long s_vr_e = System.nanoTime();

                long s_cs_s = System.nanoTime();
                double sim = SimilarityCalculation.cosineAlgorithm(tempv, dbv);
                if (Double.isNaN(sim)) {
                    sim = 0;
                }
                long s_cs_e = System.nanoTime();

                long s_pt_s = System.nanoTime();
                tempMap.put(file_database, sim);
                long s_pt_e = System.nanoTime();

                long rc = s_rc_e - s_rc_s;
                long vr = s_vr_e - s_vr_s;
                long cs = s_cs_e - s_cs_s;
                long pt = s_pt_e - s_pt_s;

                s_rc += rc;
                s_vr += vr;
                s_cs += cs;
                s_pt += pt;
            }
            long compare_End = System.nanoTime();

            long result_Start = System.nanoTime();
            Map<String, Double> simMap = MapSorter.sortByValuesDesc(tempMap);
            Map.Entry<String, Double> first = simMap.entrySet().iterator().next();
            String key = first.getKey();
            double value = first.getValue();
            long result_End = System.nanoTime();

            long total_End = System.nanoTime();

            long total_Time = total_End - total_Start;
            long read_time = read_End - read_Start;
            long create_time = create_End - create_Start;
            long compare_time = compare_End - compare_Start;
            long result_time = result_End - result_Start;

            key = key.substring(0, key.length() - 4);

            String result = key + " " + value + " " + total_Time + " " + read_time + " " + create_time + " "
                    + compare_time + " " + s_rc + " " + s_vr + " " + s_cs + " " + s_pt + " " + result_time + "\r\n";

            OutPuter.text_writer(result, searchInfo);
        }
    }

    public static void singlePdfSearch(String pdf_publish, String pdf_authory, String temp_publish, String temp_authory) throws IOException {

        MainReader.articleReader(pdf_publish, temp_publish);
        MainReader.articleReader(pdf_authory, temp_authory);

        FingerPrint tree_publish = MainReader.constructReader(temp_publish);
        FingerPrint tree_authory = MainReader.constructReader(temp_authory);

        int topWord_publish = tree_publish.getTopWords() / 4;
        int minAppr_publish = tree_publish.getMinApprs() * 4;
        Map topList_publish = tree_publish.topWordsMap(topWord_publish);

        int topWord_authory = tree_authory.getTopWords() / 4;
        int minAppr_authory = tree_authory.getMinApprs() * 4;
        Map topList_authory = tree_authory.topWordsMap(topWord_authory);

        TreeMap codition_publish = WordsFilter.minApprFilter(minAppr_publish, topList_publish);

        TreeMap codition_authory_temp = WordsFilter.minApprFilter(minAppr_authory, topList_authory);

        TreeMap codition_authory = SimilarityCalculation.extraVector(codition_publish, codition_authory_temp);

        int[] vector_publish = SimilarityCalculation.createVector(codition_publish);
        int[] vector_authory = SimilarityCalculation.createVector(codition_authory);

        double sim = SimilarityCalculation.cosineAlgorithm(vector_publish, vector_authory);

        System.out.println("Sim :" + sim);

    }

    public static void pdfSearch(String pdf_publish_path, String pdf_authory_path,
            String temp_publish, String temp_authory, String resultInfo, String searchInfo) throws IOException {

        String[] all_fileNames_publish = DocumentHelper.getFileName(pdf_publish_path);
        String[] all_fileNames_authory = DocumentHelper.getFileName(pdf_authory_path);

        for (String fileName_publish : all_fileNames_publish) {

            long startTime = System.nanoTime();

            String pdf_publish = pdf_publish_path + fileName_publish;

            MainReader.articleReader(pdf_publish, temp_publish);
            FingerPrint tree_publish = MainReader.constructReader(temp_publish);

            int topWord_publish = tree_publish.getTopWords() / 4;
            int minAppr_publish = tree_publish.getMinApprs() * 4;
            Map topList_publish = tree_publish.topWordsMap(topWord_publish);
            TreeMap codition_publish = WordsFilter.minApprFilter(minAppr_publish, topList_publish);

            for (String fileName_authory : all_fileNames_authory) {

                String pdf_authory = pdf_authory_path + fileName_authory;

                MainReader.articleReader(pdf_authory, temp_authory);
                FingerPrint tree_authory = MainReader.constructReader(temp_authory);

                int topWord_authory = tree_authory.getTopWords() / 4;
                int minAppr_authory = tree_authory.getMinApprs() * 4;
                Map topList_authory = tree_authory.topWordsMap(topWord_authory);
                TreeMap temp = WordsFilter.minApprFilter(minAppr_authory, topList_authory);

                TreeMap codition_authory = SimilarityCalculation.extraVector(codition_publish, temp);

                int[] vector_author = SimilarityCalculation.createVector(codition_publish);
                int[] vector_authory = SimilarityCalculation.createVector(codition_authory);

                double sim = SimilarityCalculation.cosineAlgorithm(vector_author, vector_authory);

                String pname = fileName_publish.substring(0, fileName_publish.length() - 4);
                String aname = fileName_authory.substring(0, fileName_authory.length() - 4);

                String result = pname + " " + aname + " " + sim + "\r\n";

                FileOutputStream output_result = new FileOutputStream(resultInfo, true);
                try (Writer writer = new OutputStreamWriter(output_result, "utf-8")) {
                    writer.write(result);
                }
            }

            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;

            String pname = fileName_publish.substring(0, fileName_publish.length() - 4);
            String searchInfor = pname + " " + executionTime + " ms \r\n";

            OutPuter.text_writer(searchInfor, searchInfo);
        }
    }
}
