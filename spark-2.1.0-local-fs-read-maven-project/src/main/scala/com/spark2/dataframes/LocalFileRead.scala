package com.spark2.dataframes

import org.apache.spark.sql.SparkSession

object LocalFileRead {

  val spark = SparkSession.builder().appName("Handling-Nulls").master("local[*]")
    .getOrCreate()
  def main(args: Array[String]): Unit = {

    val employeeData = spark.sparkContext.textFile("/home/ubuntu/spark-2.1.0-examples/input/emp.txt")
    import spark.implicits._

    employeeData.toDF().show()

  }
}
