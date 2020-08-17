package ddosprotector

import org.scalatra.test.scalatest._

class DDOSServletTests extends ScalatraFunSuite {

  addServlet(classOf[DDOSServlet], "/*")

  test("GET / on DDOSServlet should return status 200") {
    get("/") {
      status should equal (200)
    }
  }

}
