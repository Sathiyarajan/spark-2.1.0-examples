package com.spark2.hdfs
import org.apache.spark.sql.SparkSession

object SparkHDFSRead extends App {

  val spark = SparkSession
    .builder
    .appName("SparkHdfsLR")
    .master("local[*]")
    .getOrCreate()
  // read a file as dataframe
  // val lines = spark.read.textFile("hdfs://localhost:9000/spark/input/lines").toDF()
  //println(lines.count())
  //lines.show()
  val f = spark.sparkContext.textFile("hdfs://localhost:9000/spark/input/lines")
  //f.count()
  val wc = f.flatMap(l => l.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
  wc.map(println)
  println(wc.count())
  //wc.foreach(x=> println(x))
  wc.saveAsTextFile("hdfs://localhost:9000/spark/output/hdfs/")
}