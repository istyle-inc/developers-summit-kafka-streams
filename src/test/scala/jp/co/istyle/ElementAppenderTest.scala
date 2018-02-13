package jp.co.istyle

import java.io.File

import org.apache.commons.io.FileUtils
import org.junit._

class ElementAppenderTest {
  private val ex: String = "{\"FirstName\":\"LAURIE\",\"LastName\":\"MADINE\",\"Designation\":\"Delivery Manager\",\"Salary\":\"93000\",\"DateOfJoining\":\"1999-02-01\",\"Address\":\"100 Ocean Dr. Chapel Hill, NC 27516\",\"Gender\":\"Female\",\"Age\":52,\"MaritalStatus\":\"Married\",\"Interests\":\"Internet,Developers Summit\"}"
  @Test
  def shouldBe(): Unit = {
    val str = FileUtils.readFileToString(
      new File(
        getClass.getClassLoader.getResource("data.json").getPath
      ),
      "utf-8"
    )
    val md = new ElementAppender(str)
    Assert.assertEquals(ex, md.append())
  }
}
