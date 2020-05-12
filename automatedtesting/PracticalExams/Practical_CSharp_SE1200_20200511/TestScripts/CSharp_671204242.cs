using Microsoft.VisualStudio.TestTools.UnitTesting;
using TemplateAutomatedTest.Controllers;
using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using Tests.Models;
using Newtonsoft.Json;
using System.Data.SqlClient;
using Student;
using Tests.Ultilities;

namespace AutomatedTests
{
    [TestClass()]
    public class AutomatedTests
    {
        public String questionPointStr = "Question1:2-Question2:1-Question3:2-Question4:3-Question5:2";
        public static ResultSocket resultSocket;
        public static String StudentCode;
        public static int totalResut = 0;

        [ClassInitialize()]
        public static void ClassInit(TestContext TestContext)
        {
            StudentCode = Utils.GetStudentCode();
            resultSocket = Utils.GetResultSocket(StudentCode);
            resultSocket.ListQuestions = null;
            resultSocket.TotalPoint = 0;
        }


        public TestContext TestContext { get; set; }
        //start
        [TestMethod()]
        public void Question1()
        {
            Assert.AreEqual(true,TemplateQuestion.CheckLogin("phatltse62882", "123456"));
        }
        [TestMethod()]
        public void Question2()
        {
            Assert.AreEqual(true, TemplateQuestion.CheckSearchBook("C"));
        }
        [TestMethod()]
        public void Question3()
        {
            Assert.AreEqual(true, TemplateQuestion.InsertBook("CSHARP","580000"));
        }
        [TestMethod()]
        public void Question4()
        {
            Assert.AreEqual(true, TemplateQuestion.UpdateBook(5,"CSHARP", "9999"));
        }
        [TestMethod()]

        public void Question5()
        {
            Assert.AreEqual(true, TemplateQuestion.DeleteBook(3));
        }
        //end
        [TestCleanup()]
        public void TestCleanup()
        {
            if(resultSocket.ListQuestions == null)
            {
                resultSocket.ListQuestions = Utils.splitQuestions(questionPointStr);
            }
            String testResult = TestContext.CurrentTestOutcome.ToString();
            String testName = TestContext.TestName;
            String result = testName + ":" + testResult;
            if (testResult.Equals("Passed") && resultSocket.ListQuestions.ContainsKey(testName))
            {
                String questionPoint = resultSocket.ListQuestions[testName];
                resultSocket.TotalPoint += Double.Parse(resultSocket.ListQuestions[testName]);
                resultSocket.ListQuestions[testName] = questionPoint+"/" + questionPoint;
                totalResut += 1;
            }else
            {
                String questionPoint = resultSocket.ListQuestions[testName];
                resultSocket.ListQuestions[testName] = "0/"+questionPoint;
            }
            resultSocket.EvaluateTime = DateTime.Now.ToString();
        }
        [ClassCleanup()]
        public static void EndOfTest()
        {
            Console.WriteLine("End of Test");
            resultSocket.Result = totalResut+"/"+resultSocket.ListQuestions.Count;
            String jsonSerialize = JsonConvert.SerializeObject(resultSocket);
            Console.WriteLine(jsonSerialize);
            Utils.PrintResult(jsonSerialize, StudentCode);
        }
    }
}


