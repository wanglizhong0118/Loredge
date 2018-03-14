/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Reader.DocumentHelper;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import util.OutPuter;

/**
 *
 * @author allwi
 */
public class ResultReader {

    public static void average_MILE(String result_address, int index, String ave_address) throws IOException {

        String[] fileNames = DocumentHelper.getFileName(result_address);
        long si = fileNames.length;

        String author = "";
        String similarity = "";

        long sum_total = 0;
        long sum_read = 0;
        long sum_create = 0;
        long sum_match = 0;
        long sum_result = 0;

        for (String fileName : fileNames) {
            try (final FileInputStream fs = new FileInputStream(result_address + fileName);
                    final BufferedReader reader = new BufferedReader(new InputStreamReader(fs, "utf-8"), 5 * 1024 * 1024)) {
                String line;
                int thisline = 1;
                while ((line = reader.readLine()) != null) {
                    if (thisline == index) {
                        String[] words = line.split("\r");
                        for (String s : words) {
                            //  System.out.println(s);
                            String[] sa = s.split("\\s+");

                            author = sa[0];
                            similarity = sa[1];

                            long tsum_total = Long.parseLong(sa[2]);
                            sum_total += tsum_total;

                            long tsum_read = Long.parseLong(sa[3]);
                            sum_read += tsum_read;

                            long tsum_create = Long.parseLong(sa[4]);
                            sum_create += tsum_create;

                            long tsum_match = Long.parseLong(sa[5]);
                            sum_match += tsum_match;

                            long tsum_result = Long.parseLong(sa[6]);
                            sum_result += tsum_result;

                        }
                    }
                    thisline++;
                }
            }
        }
        long ave_total = sum_total / si;
        long ave_read = sum_read / si;
        long ave_create = sum_create / si;
        long ave_match = sum_match / si;
        long ave_result = sum_result / si;

        String results = index + " " + author + " " + similarity + " " + ave_total + " " + ave_read + " " + ave_create + " " + ave_match + " " + ave_result + " \r\n";

        OutPuter.text_writer(results, ave_address);

    }

    public static void average_NANO(String result_address, int index, String ave_address) throws IOException {

        String[] fileNames = DocumentHelper.getFileName(result_address);
        long si = fileNames.length;

        String author = "";
        String similarity = "";

        long sum_total = 0;
        long sum_read = 0;
        long sum_create = 0;
        long sum_match = 0;
        long sum_s_read = 0;
        long sum_s_create = 0;
        long sum_s_calcu = 0;
        long sum_s_put = 0;
        long sum_result = 0;

        for (String fileName : fileNames) {
            try (final FileInputStream fs = new FileInputStream(result_address + fileName);
                    final BufferedReader reader = new BufferedReader(new InputStreamReader(fs, "utf-8"), 5 * 1024 * 1024)) {
                String line;
                int thisline = 1;
                while ((line = reader.readLine()) != null) {
                    if (thisline == index) {
                        String[] words = line.split("\r");
                        for (String s : words) {
                            //  System.out.println(s);
                            String[] sa = s.split("\\s+");

                            author = sa[0];
                            similarity = sa[1];

                            long tsum_total = Long.parseLong(sa[2]);
                            sum_total += tsum_total;

                            long tsum_read = Long.parseLong(sa[3]);
                            sum_read += tsum_read;

                            long tsum_create = Long.parseLong(sa[4]);
                            sum_create += tsum_create;

                            long tsum_match = Long.parseLong(sa[5]);
                            sum_match += tsum_match;

                            long tsum_s_read = Long.parseLong(sa[6]);
                            sum_s_read += tsum_s_read;

                            long tsum_s_create = Long.parseLong(sa[7]);
                            sum_s_create += tsum_s_create;

                            long tsum_s_calcu = Long.parseLong(sa[8]);
                            sum_s_calcu += tsum_s_calcu;

                            long tsum_s_put = Long.parseLong(sa[9]);
                            sum_s_put += tsum_s_put;

                            long tsum_result = Long.parseLong(sa[10]);
                            sum_result += tsum_result;

                        }
                    }
                    thisline++;
                }
            }
        }
        long ave_total = sum_total / si;
        long ave_read = sum_read / si;
        long ave_create = sum_create / si;
        long ave_match = sum_match / si;
        long ave_s_read = sum_s_read / si;
        long ave_s_create = sum_s_create / si;
        long ave_s_calcu = sum_s_calcu / si;
        long ave_s_put = sum_s_put / si;
        long ave_result = sum_result / si;

        String results = index + " " + author + " " + similarity + " " + ave_total + " " + ave_read + " " + ave_create + " " + ave_match
                + " " + ave_s_read + " " + ave_s_create + " " + ave_s_calcu + " " + ave_s_put + " " + ave_result + " \r\n";

        OutPuter.text_writer(results, ave_address);

    }

}
