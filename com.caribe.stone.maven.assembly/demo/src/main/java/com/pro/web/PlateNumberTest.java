package com.pro.web;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.BooleanClause.Occur;

 

 

public class PlateNumberTest {

//    String IDNEX_READER_PATH = "d:/test_index/allInOne";
    String IDNEX_READER_PATH = "D:/test_index";
    String IDNEX_WRITER_PATH = "d:/paoding_test_index";
    
//    String IDNEX_READER_PATH = "/home/bcw/search";
//    String IDNEX_WRITER_PATH = "/home/bcw/search";

    

    public static void main(String[] args) throws Exception {

       

       PlateNumberTest test = new PlateNumberTest();

 

//     test.IDNEX_PATH = args[0];

//     test.count = Integer.parseInt(args[1]);

       

       //query current record count

       Thread counter = test.new Counter(test);

//       counter.start();
//       //create index
//       test.create();
//       //interrupt counter
//       counter.interrupt();

       

       //quyer record

       test.query();

       

    }

    

    /*

     * test result

     * 

       沪C10001(TermQuery)

       search time : 354

       count : 1

       get time : 7

 

       沪C*000*

       search time : 5050

       count : 1684

       get time : 281

       Max memory:23m(get data)

                 22m(don't get data)

       

       沪C2000*

       search time : 373

       count : 36

       get time : 6

       

       

       沪C*0*0*

       search time : 15491

       count : 216316

       get time : 82095

       Max memory:650m(get data)

                 400m(don't get data)

 

     */

    public void query() throws Exception {

       IndexReader reader = IndexReader.open(IDNEX_READER_PATH);

       Searcher searcher = new IndexSearcher(reader);

       Query query;

//     query = new FuzzyQuery(new Term("plate","0000"));

       String plate = "***45";

       System.out.println(plate);

       query = new WildcardQuery(new Term("plate", plate));

//     query = new TermQuery(new Term("plate", plate));
       
       BooleanQuery booleanQuery = new BooleanQuery();
       booleanQuery.add(query, Occur.MUST);
       
//       Query fieldQuery = new TermQuery(new Term("field","沪C"));
//       Query fieldQuery = new WildcardQuery(new Term("field","沪*"));
       
//       booleanQuery.add(fieldQuery,Occur.MUST);

       //avoid exception(BooleanQuery$TooManyClauses: maxClauseCount is set to 1024)

       BooleanQuery.setMaxClauseCount(10000000);

       

       long s = System.currentTimeMillis();

       Hits hits = searcher.search(booleanQuery);       

       int count = hits.length();

       System.out.println("search time : " + (System.currentTimeMillis() - s));

       System.out.println("count : " + count);

       

       s = System.currentTimeMillis();

//       for(int i = 0;i < count; i ++){
//
//           Document doc = hits.doc(i);
//
////         System.out.println(
//
//                  doc.getField("plate");
//
////                );
//
//       }

       System.out.println("get time : " + (System.currentTimeMillis() - s));

 

    }

    private int t;

    private int count = 10000000;

    public void create() throws Exception {

       

       Analyzer analyzer = null;//new CJKAnalyzer();

 

       //建立索引

 

       IndexWriter writer = new IndexWriter(IDNEX_WRITER_PATH, analyzer, true);

//     writer.set

 

       Document doc;

       Field field;

       

       

       

       for(t = 0; t < count; t ++){

           doc = new Document();

           field = new Field("id",String.valueOf(t),

                  Field.Store.YES,Field.Index.UN_TOKENIZED,Field.TermVector.NO);

           doc.add(field);

           field = new Field("plate",getPlate(),

                  Field.Store.YES,Field.Index.UN_TOKENIZED,Field.TermVector.NO);
           
           doc.add(field);
           
           field = new Field("field","沪C",

                   Field.Store.YES,Field.Index.UN_TOKENIZED,Field.TermVector.NO);

           doc.add(field);

//         field = new Field("time",String.valueOf(System.currentTimeMillis()),

//                Field.Store.YES,Field.Index.UN_TOKENIZED,Field.TermVector.NO);

//         doc.add(field);

           writer.addDocument(doc);

       }

       

       

       

       writer.close();

 

       System.out.println("Indexed success!");

    }

    private String[] s = new String[]{"0","1","2","3","4","5","6","7","8","9",

           "A","B","C","D","E","F","G","H","I","J","K","L","M","N",

           "O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    private int[] index = new int[5];

    private String getPlate(){

       StringBuilder plate = new StringBuilder();

       addIndex(0);

       for(int i = 4; i >= 0; i --){

           plate.append(s[index[i]]);

       }

     System.out.println(plate);

       return plate.toString();

    }

    private void addIndex(int curIndex){

       if(curIndex > 4)return;

       index[curIndex] ++;

       if(index[curIndex] >= 36){

           index[curIndex] = 0;

           addIndex(++curIndex);

       }

    }

    

    private class Counter extends Thread {

       private PlateNumberTest test;

       public Counter(PlateNumberTest test){

           this.test = test;

       }

       public void run(){

           while(true){

              System.out.println(test.t);

              try {

                  Thread.sleep(30000);

              } catch (InterruptedException e) {

                  e.printStackTrace();

              }

           }

       }

    }

}

 

