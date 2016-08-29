package readingfiles;

import com.datumbox.framework.applications.nlp.TextClassifier;
import com.datumbox.framework.common.Configuration;
import com.datumbox.framework.common.dataobjects.Record;
import com.datumbox.framework.common.utilities.PHPMethods;
import com.datumbox.framework.common.utilities.RandomGenerator;
import com.datumbox.framework.core.machinelearning.classification.MultinomialNaiveBayes;
import com.datumbox.framework.core.machinelearning.common.interfaces.ValidationMetrics;
import com.datumbox.framework.core.machinelearning.featureselection.categorical.ChisquareSelect;
import com.datumbox.framework.core.utilities.text.extractors.NgramsExtractor;
import com.datumbox.framework.lib.Datumbox;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Text Classification example.
 * 
 * @author Vasilis Vryniotis <bbriniotis@datumbox.com>
 */
public class DatumboxTwitterSenti {
    
    /**
     * Example of how to use the TextClassifier class.
     * 
     * @param args the command line arguments
     * @throws java.net.URISyntaxException
     * @throws IOException 
     * @throws SQLException 
     */
    public String Sentiment(String tweet) throws URISyntaxException, IOException {        
        /**
         * There are two configuration files in the resources folder:
         * 
         * - datumbox.config.properties: It contains the configuration for the storage engines (required)
         * - logback.xml: It contains the configuration file for the logger (optional)
         */
    	
    	
    	//System.setOut(new PrintStream(new FileOutputStream("/home/vpotla/Desktop/StreamingTweets.txt")));//writing console output to a file
        
        //Initialization
        //--------------
        RandomGenerator.setGlobalSeed(42L); //optionally set a specific seed for all Random objects
        Configuration conf = Configuration.getConfiguration(); //default configuration based on properties file
        //conf.setDbConfig(new InMemoryConfiguration()); //use In-Memory storage (default)
        //conf.setDbConfig(new MapDBConfiguration()); //use MapDB storage
        //conf.getConcurrencyConfig().setParallelized(true); //turn on/off the parallelization
        //conf.getConcurrencyConfig().setMaxNumberOfThreadsPerTask(4); //set the concurrency level
        
        
        
        //Reading Data
        //------------
        Map<Object, URI> datasets = new HashMap<Object, URI>(); //The examples of each category are stored on the same file, one example per row.
        datasets.put("positive", Datumbox.class.getClassLoader().getResource("datasets/sentiment-analysis/Datasets/Main/Sports_News_All/All.pos").toURI());
        datasets.put("negative", Datumbox.class.getClassLoader().getResource("datasets/sentiment-analysis/Datasets/Main/Sports_News_All/All.neg").toURI());
        datasets.put("neutral", Datumbox.class.getClassLoader().getResource("datasets/sentiment-analysis/Datasets/Main/Sports_News_All/All.neu").toURI());

        
        //jar:http://www.foo.com/bar/baz.jar!/         
        //Setup Training Parameters
        //-------------------------
        TextClassifier.TrainingParameters trainingParameters = new TextClassifier.TrainingParameters();
        
        //Classifier configuration
        trainingParameters.setModelerClass(MultinomialNaiveBayes.class);
        trainingParameters.setModelerTrainingParameters(new MultinomialNaiveBayes.TrainingParameters());
        
        //Set data transfomation configuration
        trainingParameters.setDataTransformerClass(null);
        trainingParameters.setDataTransformerTrainingParameters(null);
        
        //Set feature selection configuration
        trainingParameters.setFeatureSelectorClass(ChisquareSelect.class);
        trainingParameters.setFeatureSelectorTrainingParameters(new ChisquareSelect.TrainingParameters());
        
        //Set text extraction configuration
        trainingParameters.setTextExtractorClass(NgramsExtractor.class);
        trainingParameters.setTextExtractorParameters(new NgramsExtractor.Parameters());
        

        //Fit the classifier
        //------------------
        TextClassifier classifier = new TextClassifier("TwitterSentimentAnalysis", conf);
        classifier.fit(datasets, trainingParameters);
 
        //Use the classifier
        //------------------
        
        //Get validation metrics on the training set
        ValidationMetrics vm = classifier.validate(datasets);
        classifier.setValidationMetrics(vm); //store them in the model for future reference
         
        int count = 0;
       
        String sentence = tweet;
        Record r = classifier.predict(sentence);

       // System.setOut(p);
        //count++;
        System.out.println("Classifing sentence: \""+sentence+"\"");
        System.out.println("Predicted class: "+r.getYPredicted());
       
        //System.out.println(count);
        System.out.println("Done");
        
  
        //Delete the classifier. This removes all files.
        classifier.delete();
		return sentence;
     
 	}
   }


