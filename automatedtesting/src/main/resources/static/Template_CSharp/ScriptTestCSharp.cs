using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Text;

namespace TemplateAutomatedTest.Template.Tests
{
    [TestClass()]
    public class TemplateQuestionTests
    {
        public TestContext TestContext { get; set; }
        //start
        [TestMethod()]
        public void Question1Test()
        {
            Assert.AreEqual(5, TemplateQuestion.Question1(2, 3));
        }
        [TestMethod()]
        public void Question2Test()
        {
            Assert.AreEqual(4, TemplateQuestion.Question2(5, 1));
        }
        //end
        [TestCleanup]
        public void TestCleanup()
        {
            String testResult = TestContext.CurrentTestOutcome.ToString();
            String testName = TestContext.TestName;
            String result = testName + " : " + testResult;
            String path = @"C:\Users\ADMIN\source\repos\TemplateAutomatedTest\TemplateAutomatedTest\Ultilities\Result.txt";
            Ultilities.TestResult.WriteResult(path,result);
        }
    }
}