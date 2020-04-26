using Microsoft.VisualStudio.TestTools.UnitTesting;
using TemplateAutomatedTest.Controllers;
using System;
using System.Collections.Generic;
using System.Text;
using TemplateAutomatedTest.Template;
using System.IO;
using Tests.Models;
using Newtonsoft.Json;

namespace AutomatedTests
{
    [TestClass()]
    public class AutomatedTests
    {
        public String questionPointStr = "1";
        public static ResultSocket resultSocket;
        //[TestInitialize]
        //public void TestInit()
        //{
        //    resultSocket = new ResultSocket();
        //}
        [ClassInitialize()]
        public static void ClassInit(TestContext TestContext)
        {
            resultSocket = new ResultSocket();
        }


        public TestContext TestContext { get; set; }
        //start
@Test 
@Order(1) 
public void testcase(){driver.findElement(By.id($paramName)).clear(); driver.findElement(By.id($paramName)).sendKeys($paramValue);}
//end
        [TestCleanup()]
        public void TestCleanup()
        {
            if(resultSocket.ListQuestions == null)
            {
                resultSocket.ListQuestions = Tests.Ultilities.TestResult.splitQuestions(questionPointStr);
            }
            String testResult = TestContext.CurrentTestOutcome.ToString();
            String testName = TestContext.TestName;
            String result = testName + " : " + testResult;
            if (testResult.Equals("Passed") && resultSocket.ListQuestions.ContainsKey(testName))
            {
                resultSocket.TotalPoint += Double.Parse(resultSocket.ListQuestions[testName]);
                resultSocket.Result += 1;
            }else
            {
                resultSocket.ListQuestions[testName] = "0";
            }
            resultSocket.Time = DateTime.Now.ToString();
            String path = System.AppDomain.CurrentDomain.BaseDirectory + @"Result.txt";
            Console.WriteLine(path);
            Tests.Ultilities.TestResult.WriteResult(path, result);
        }
        [ClassCleanup()]
        public static void EndOfTest()
        {
            String jsonSerialize = JsonConvert.SerializeObject(resultSocket);
            Console.WriteLine(jsonSerialize);
            Tests.Ultilities.TestResult.connectToServer("127.0.0.1", 9997,jsonSerialize);

        }
    }
}


