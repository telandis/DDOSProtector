package ddosprotector

import java.io.{BufferedWriter, File, FileWriter}
import java.util
import java.util.Calendar
import java.util.concurrent._

class DDOSRecorder() {

  class LRUCache(var lruCapacity: Int) extends util.LinkedHashMap[String, Integer](lruCapacity, 0.75F, true) {

    def get(key: String): Int = super.getOrDefault(key, -1)

    def put(key: String): Unit = {
      val newCount = super.get(key)+1
      if(newCount == 5) {
        println("Writing offending address: " + key)
        val file = new File("OffendingAddresses.txt")
        val bw = new BufferedWriter(new FileWriter(file, true))
        bw.append(Calendar.getInstance().getTime() + ": " + key + "\n")
        bw.close()
      }
      super.put(key, newCount)
    }

    override protected def removeEldestEntry(eldest: util.Map.Entry[String, Integer]): Boolean = size > lruCapacity
  }

  println("DDOSRecorder - Constructor  BEGIN .... ")

  val lruCache = new LRUCache(10)

  val ex = new ScheduledThreadPoolExecutor(1)

  val task = new Runnable {
    def run() = {
      println("Clearing the cache, printing contents of the cache:")
      for (key <- lruCache.keySet().toArray()) {
        println("    " + key + " => " + lruCache.get(key) + " hits")
      }
      lruCache.clear
    }
  }

  val f = ex.scheduleAtFixedRate(task, 15, 15, TimeUnit.SECONDS)

  def hit(address: String): Unit = {
    lruCache.put(address)
  }

  println("DDOSRecorder - Constructor END .... ")
}

object DDOSRecorder {

}