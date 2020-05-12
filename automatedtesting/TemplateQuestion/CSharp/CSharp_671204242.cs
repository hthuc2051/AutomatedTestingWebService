using Practical_CSharp.Student.Repository;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Student
{
    public class TemplateQuestion
    {
        public static Boolean CheckLogin(String username,String password)
        {
            if(AdminDAO.CheckLogin(username,password))
            {
                return true;
            }
            return false;
        }

        public static Boolean CheckSearchBook(String name)
        {

            var data = BookDAO.SearchByName(name);
            var dataSet = new DataSet();
            data.Fill(dataSet);
            if (dataSet.Tables[0].Rows.Count != 0)
            {
                return true;
            }
            return false;
        }

        public static Boolean InsertBook(String name,String price)
        {
            if (BookDAO.InsertBook(name,price))
            {
                return true;
            }
            return false;
        }

        public static Boolean DeleteBook(int id)
        {
            if (BookDAO.DeleteBook(id))
            {
                return true;
            }
            return false;
        }

        public static Boolean UpdateBook(int id, String name, String price)
        {
            if (BookDAO.UpdateBook(id,name,price))
            {
                return true;
            }
            return false;
        }

    }
}
