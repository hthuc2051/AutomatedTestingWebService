USE [master]
GO
/****** Object:  Database [PE2ADB]    Script Date: 3/5/2020 5:02:18 PM ******/
CREATE DATABASE [PE2ADB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PE2ADB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\PE2ADB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PE2ADB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\PE2ADB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [PE2ADB] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PE2ADB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PE2ADB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PE2ADB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PE2ADB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PE2ADB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PE2ADB] SET ARITHABORT OFF 
GO
ALTER DATABASE [PE2ADB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PE2ADB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PE2ADB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PE2ADB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PE2ADB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PE2ADB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PE2ADB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PE2ADB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PE2ADB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PE2ADB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PE2ADB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PE2ADB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PE2ADB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PE2ADB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PE2ADB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PE2ADB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PE2ADB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PE2ADB] SET RECOVERY FULL 
GO
ALTER DATABASE [PE2ADB] SET  MULTI_USER 
GO
ALTER DATABASE [PE2ADB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PE2ADB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PE2ADB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PE2ADB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PE2ADB] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'PE2ADB', N'ON'
GO
ALTER DATABASE [PE2ADB] SET QUERY_STORE = OFF
GO
USE [PE2ADB]
GO
/****** Object:  Table [dbo].[Action]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Action](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](150) NULL,
	[code] [nvarchar](max) NULL,
	[subject_id] [int] NULL,
	[admin_id] [int] NOT NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_Action] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Admin]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Admin](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NOT NULL,
	[first_name] [nvarchar](100) NULL,
	[middle_name] [nvarchar](100) NULL,
	[last_name] [nvarchar](100) NULL,
	[email] [nvarchar](100) NULL,
	[contact_phone] [nvarchar](100) NULL,
	[active] [bit] NOT NULL,
 CONSTRAINT [PK_Admin] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Class]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Class](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[class_code] [varchar](50) NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_Class] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HeadLecturer]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HeadLecturer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NOT NULL,
	[first_name] [nvarchar](100) NULL,
	[middle_name] [nvarchar](100) NULL,
	[last_name] [nvarchar](100) NULL,
	[email] [nvarchar](500) NULL,
	[contact_phone] [nvarchar](50) NULL,
	[active] [bit] NOT NULL,
 CONSTRAINT [PK_HeadLecturer] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lecturer]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lecturer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NOT NULL,
	[enroll_key] [nvarchar](50) NULL,
	[first_name] [nvarchar](100) NULL,
	[middle_name] [nvarchar](100) NULL,
	[last_name] [nvarchar](100) NULL,
	[email] [nvarchar](100) NULL,
	[contact_phone] [nvarchar](100) NULL,
	[active] [bit] NOT NULL,
 CONSTRAINT [PK_Lecturer] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Parameter]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Param](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[type] [varchar](50) NULL,
	[action_id] [int] NOT NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_Param] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PracticalExam]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PracticalExam](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [date] NULL,
	[code] [nvarchar](150) NULL,
	[state] [nvarchar](50) NULL,
	[subject_class_id] [int] NOT NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_Practical] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PracticalExam_Script]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PracticalExam_Script](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[practical_exam_id] [int] NOT NULL,
	[script_id] [int] NOT NULL,
 CONSTRAINT [PK_Practical_Script] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Script]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Script](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[subject_id] [int] NOT NULL,
	[headlecturer_id] [int] NOT NULL,
	[code] [nvarchar](250) NULL,
	[name] [nvarchar](150) NULL,
	[time_created] [datetime] NULL,
	[script_path] [nvarchar](100) NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_Script] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [nvarchar](50) NOT NULL,
	[name] [nvarchar](50) NULL,
	[email] [nvarchar](50) NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_Student] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subject]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subject](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[code] [varchar](50) NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_Subject] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subject_Class]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subject_Class](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[subject_id] [int] NULL,
	[class_id] [int] NULL,
	[lecturer_id] [int] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_Subject_Class] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subject_Class_Student]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subject_Class_Student](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[subject_class_id] [int] NULL,
	[student_id] [int] NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Class_Student] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Submission]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Submission](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[time_submitted] [datetime] NULL,
	[submit_path] [nvarchar](100) NULL,
	[script_code] [nvarchar](150) NULL,
	[point] [float] NULL,
	[student_id] [int] NULL,
	[practical_exam_id] [int] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_Submission] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 3/5/2020 5:02:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](150) NOT NULL,
	[password] [nvarchar](150) NOT NULL,
	[role] [nvarchar](50) NOT NULL,
	[active] [bit] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Index [IX_Practical_Script]    Script Date: 3/5/2020 5:02:19 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_Practical_Script] ON [dbo].[PracticalExam_Script]
(
	[practical_exam_id] ASC,
	[script_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [IX_Submission_Student]    Script Date: 3/5/2020 5:02:19 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_Submission_Student] ON [dbo].[Subject_Class_Student]
(
	[student_id] ASC,
	[subject_class_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_User]    Script Date: 3/5/2020 5:02:19 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_User] ON [dbo].[User]
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Action]  WITH CHECK ADD  CONSTRAINT [FK_Action_Admin] FOREIGN KEY([admin_id])
REFERENCES [dbo].[Admin] ([id])
GO
ALTER TABLE [dbo].[Action] CHECK CONSTRAINT [FK_Action_Admin]
GO
ALTER TABLE [dbo].[Action]  WITH CHECK ADD  CONSTRAINT [FK_Action_Subject] FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subject] ([id])
GO
ALTER TABLE [dbo].[Action] CHECK CONSTRAINT [FK_Action_Subject]
GO
ALTER TABLE [dbo].[Admin]  WITH CHECK ADD  CONSTRAINT [FK_Admin_User] FOREIGN KEY([user_id])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[Admin] CHECK CONSTRAINT [FK_Admin_User]
GO
ALTER TABLE [dbo].[HeadLecturer]  WITH CHECK ADD  CONSTRAINT [FK_HeadLecturer_User] FOREIGN KEY([user_id])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[HeadLecturer] CHECK CONSTRAINT [FK_HeadLecturer_User]
GO
ALTER TABLE [dbo].[Lecturer]  WITH CHECK ADD  CONSTRAINT [FK_Lecturer_User] FOREIGN KEY([user_id])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[Lecturer] CHECK CONSTRAINT [FK_Lecturer_User]
GO
ALTER TABLE [dbo].[Param]  WITH CHECK ADD  CONSTRAINT [FK_Param_Action] FOREIGN KEY([action_id])
REFERENCES [dbo].[Action] ([id])
GO
ALTER TABLE [dbo].[Param] CHECK CONSTRAINT [FK_Param_Action]
GO
ALTER TABLE [dbo].[PracticalExam]  WITH CHECK ADD  CONSTRAINT [FK_PracticalExam_Subject_Class] FOREIGN KEY([subject_class_id])
REFERENCES [dbo].[Subject_Class] ([id])
GO
ALTER TABLE [dbo].[PracticalExam] CHECK CONSTRAINT [FK_PracticalExam_Subject_Class]
GO
ALTER TABLE [dbo].[PracticalExam_Script]  WITH CHECK ADD  CONSTRAINT [FK_Practical_Script_Practical] FOREIGN KEY([practical_exam_id])
REFERENCES [dbo].[PracticalExam] ([id])
GO
ALTER TABLE [dbo].[PracticalExam_Script] CHECK CONSTRAINT [FK_Practical_Script_Practical]
GO
ALTER TABLE [dbo].[PracticalExam_Script]  WITH CHECK ADD  CONSTRAINT [FK_PracticalExam_Script_Script] FOREIGN KEY([script_id])
REFERENCES [dbo].[Script] ([id])
GO
ALTER TABLE [dbo].[PracticalExam_Script] CHECK CONSTRAINT [FK_PracticalExam_Script_Script]
GO
ALTER TABLE [dbo].[Script]  WITH CHECK ADD  CONSTRAINT [FK_Script_HeadLecturer] FOREIGN KEY([headlecturer_id])
REFERENCES [dbo].[HeadLecturer] ([id])
GO
ALTER TABLE [dbo].[Script] CHECK CONSTRAINT [FK_Script_HeadLecturer]
GO
ALTER TABLE [dbo].[Script]  WITH CHECK ADD  CONSTRAINT [FK_Script_Subject] FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subject] ([id])
GO
ALTER TABLE [dbo].[Script] CHECK CONSTRAINT [FK_Script_Subject]
GO
ALTER TABLE [dbo].[Subject_Class]  WITH CHECK ADD  CONSTRAINT [FK_Subject_Class_Class] FOREIGN KEY([class_id])
REFERENCES [dbo].[Class] ([id])
GO
ALTER TABLE [dbo].[Subject_Class] CHECK CONSTRAINT [FK_Subject_Class_Class]
GO
ALTER TABLE [dbo].[Subject_Class]  WITH CHECK ADD  CONSTRAINT [FK_Subject_Class_Lecturer] FOREIGN KEY([lecturer_id])
REFERENCES [dbo].[Lecturer] ([id])
GO
ALTER TABLE [dbo].[Subject_Class] CHECK CONSTRAINT [FK_Subject_Class_Lecturer]
GO
ALTER TABLE [dbo].[Subject_Class]  WITH CHECK ADD  CONSTRAINT [FK_Subject_Class_Subject] FOREIGN KEY([subject_id])
REFERENCES [dbo].[Subject] ([id])
GO
ALTER TABLE [dbo].[Subject_Class] CHECK CONSTRAINT [FK_Subject_Class_Subject]
GO
ALTER TABLE [dbo].[Subject_Class_Student]  WITH CHECK ADD  CONSTRAINT [FK_Subject_Class_Student_Student] FOREIGN KEY([student_id])
REFERENCES [dbo].[Student] ([id])
GO
ALTER TABLE [dbo].[Subject_Class_Student] CHECK CONSTRAINT [FK_Subject_Class_Student_Student]
GO
ALTER TABLE [dbo].[Subject_Class_Student]  WITH CHECK ADD  CONSTRAINT [FK_Subject_Class_Student_Subject_Class] FOREIGN KEY([subject_class_id])
REFERENCES [dbo].[Subject_Class] ([id])
GO
ALTER TABLE [dbo].[Subject_Class_Student] CHECK CONSTRAINT [FK_Subject_Class_Student_Subject_Class]
GO
ALTER TABLE [dbo].[Submission]  WITH CHECK ADD  CONSTRAINT [FK_Submission_PracticalExam] FOREIGN KEY([practical_exam_id])
REFERENCES [dbo].[PracticalExam] ([id])
GO
ALTER TABLE [dbo].[Submission] CHECK CONSTRAINT [FK_Submission_PracticalExam]
GO
ALTER TABLE [dbo].[Submission]  WITH CHECK ADD  CONSTRAINT [FK_Submission_Student] FOREIGN KEY([student_id])
REFERENCES [dbo].[Student] ([id])
GO
ALTER TABLE [dbo].[Submission] CHECK CONSTRAINT [FK_Submission_Student]
GO
USE [master]
GO
ALTER DATABASE [PE2ADB] SET  READ_WRITE 
GO
