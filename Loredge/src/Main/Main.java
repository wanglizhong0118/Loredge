package Main;

import Comparison.DatabaseAccess_FeatureFull;
import Comparison.DatabaseAccess_FeatureHalf;
import Comparison.DatabaseAccess_CompleteVer;
import Comparison.DatabaseAccess_FeatureQuarter;
import DatabaseOperating.DataBaseCreation;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import util.OutPuter;

/**
 *
 * @author allwi
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        
        System.out.println("hello,world");

        String document_path_authors = "E://Loredge/100 articles/authors copy/";
        String document_path_publish = "E://Loredge/100 articles/published copy/";

        String ver_complete_database_authors = "E://Database/ver_complete/authors/";
        String ver_complete_infor_authors = "E://Database/ver_complete/info_authors.txt";
        String ver_complete_database_publish = "E://Database/ver_complete/publish/";
        String ver_complete_infor_publish = "E://Database/ver_complete/info_publish.txt";

        String ver_feature_full_database_authors = "E://Database/ver_feature_full/authors/";
        String ver_feature_full_infor_authors = "E://Database/ver_feature_full/info_authors.txt";
        String ver_feature_full_database_publish = "E://Database/ver_feature_full/publish/";
        String ver_feature_full_infor_publish = "E://Database/ver_feature_full/info_publish.txt";

        String ver_feature_half_database_authors = "E://Database/ver_feature_half/authors/";
        String ver_feature_half_infor_authors = "E://Database/ver_feature_half/info_authors.txt";
        String ver_feature_half_database_publish = "E://Database/ver_feature_half/publish/";
        String ver_feature_half_infor_publish = "E://Database/ver_feature_half/info_publish.txt";

        String ver_feature_quarter_database_authors = "E://Database/ver_feature_quarter/authors/";
        String ver_feature_quarter_infor_authors = "E://Database/ver_feature_quarter/info_authors.txt";
        String ver_feature_quarter_database_publish = "E://Database/ver_feature_quarter/publish/";
        String ver_feature_quarter_infor_publish = "E://Database/ver_feature_quarter/info_publish.txt";

        String temp_path_authors = "E://authors.txt";
        String temp_path_publish = "E://publish.txt";
        String temp = "E://temp.txt";

        /*
        DataBaseCreation.ver_Complete(document_path_authors, temp_path_authors, ver_complete_database_authors, ver_complete_infor_authors);
        DataBaseCreation.ver_Complete(document_path_publish, temp_path_publish, ver_complete_database_publish, ver_complete_infor_publish);
        
        DataBaseCreation.ver_FeatureFull(document_path_authors, temp_path_authors, ver_feature_full_database_authors, ver_feature_full_infor_authors);
        DataBaseCreation.ver_FeatureFull(document_path_publish, temp_path_publish, ver_feature_full_database_publish, ver_feature_full_infor_publish);
        
        DataBaseCreation.ver_FeatureHalf(document_path_authors, temp_path_authors, ver_feature_half_database_authors, ver_feature_half_infor_authors);
        DataBaseCreation.ver_FeatureHalf(document_path_publish, temp_path_publish, ver_feature_half_database_publish, ver_feature_half_infor_publish);
        
        DataBaseCreation.ver_FeatureQuarter(document_path_authors, temp_path_authors, ver_feature_quarter_database_authors, ver_feature_quarter_infor_authors);
        DataBaseCreation.ver_FeatureQuarter(document_path_publish, temp_path_publish, ver_feature_quarter_database_publish, ver_feature_quarter_infor_publish);      
         */
 /*
        String complete_result = "E://complete_reuslt.txt";
        String compelte_search = "E://complete_Info.txt";

        String featureFull_result = "E://featureFull_reuslt.txt";
        String featureFull_search = "E://featureFull_Info.txt";

        String featureHalf_result = "E://featureHalf_reuslt.txt";
        String featureHalf_search = "E://featureHalf_Info.txt";

        String featureQuarter_result = "E://featureQuarter_reuslt.txt";
        String featureQuarter_search = "E://featureQuarter_Info.txt";      
       
            DatabaseAccess_CompleteVer.pdfSearch(document_path_publish, document_path_authors,
                    temp_path_publish, temp_path_authors, complete_result, compelte_search );
            DatabaseAccess_FeatureFull.pdfSearch(document_path_publish, document_path_authors,
                    temp_path_publish, temp_path_authors, featureFull_result, featureFull_search);
            DatabaseAccess_FeatureHalf.pdfSearch(document_path_publish, document_path_authors,
                    temp_path_publish, temp_path_authors, featureHalf_result, featureHalf_search);
            DatabaseAccess_FeatureQuarter.pdfSearch(document_path_publish, document_path_authors,
                    temp_path_publish, temp_path_authors, featureQuarter_result, featureQuarter_search);
         */
 /*
        String ver_complete_searchInfo = "E://result_dtime/detailed_database/full1/complete/database_complete_result";
        String ver_feature_full_searchInfo = "E://result_dtime/detailed_database/full1/featureFull/database_featureFull_result";
        String ver_feature_half_searchInfo = "E://result_dtime/detailed_database/full1/featureHalf/database_featureHalf_result";
        String ver_feature_quarter_searchInfo = "E://result_dtime/detailed_database/full1/featureQuarter/database_featureQuarter_result";
        String time = "E://result_dtime/detailed_database/full1/time.txt";

        for (int i = 29; i < 51; i++) {
            long comt_Start = System.nanoTime();
            DatabaseAccess_CompleteVer.databaseSearch(document_path_publish, ver_complete_database_authors, ver_complete_searchInfo + "-" + i + ".txt", temp);
            long comt_End = System.nanoTime();
            long comt = comt_End - comt_Start;

            long feaF_Start = System.nanoTime();
            DatabaseAccess_FeatureFull.databaseSearch(document_path_publish, ver_feature_full_database_authors, ver_feature_full_searchInfo + "-" + i + ".txt", temp);
            long feaF_End = System.nanoTime();
            long feaF = feaF_End - feaF_Start;

            long feaH_Start = System.nanoTime();
            DatabaseAccess_FeatureHalf.databaseSearch(document_path_publish, ver_feature_half_database_authors, ver_feature_half_searchInfo + "-" + i + ".txt", temp);
            long feaH_End = System.nanoTime();
            long feaH = feaH_End - feaH_Start;

            long feaQ_Start = System.nanoTime();
            DatabaseAccess_FeatureQuarter.databaseSearch(document_path_publish, ver_feature_quarter_database_authors, ver_feature_quarter_searchInfo + "-" + i + ".txt", temp);
            long feaQ_End = System.nanoTime();
            long feaQ = feaQ_End - feaQ_Start;

            String t = comt + " " + feaF + " " + feaH + " " + feaQ + " \r\n";

            OutPuter.text_writer(t, time);
        }
         */
 /*
        String rs_complete = "E://result_dtime/Nano Second/db_full_50/complete/";
        String rs_featurefull = "E://result_dtime/Nano Second/db_full_50/featurefull/";
        String rs_featurehalf = "E://result_dtime/Nano Second/db_full_50/featurehalf/";
        String rs_featurequarter = "E://result_dtime/Nano Second/db_full_50/featurequarter/";

        String rs_complete_averange = "E://averange_complete.txt";
        String rs_featurefull_averange = "E://averange_featureFull.txt";
        String rs_featurehalf_averange = "E://averange_featureHalf.txt";
        String rs_featurequarter_averange = "E://averange_featureQuarter.txt";

        for (int i = 1; i <= 100; i++) {
            ResultReader.average_NANO(rs_complete, i, rs_complete_averange);
            ResultReader.average_NANO(rs_featurefull, i, rs_featurefull_averange);
            ResultReader.average_NANO(rs_featurehalf, i, rs_featurehalf_averange);
            ResultReader.average_NANO(rs_featurequarter, i, rs_featurequarter_averange);
        }
         */
    }

}
