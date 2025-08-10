# 🚀 Web Automation Framework (Maven + TestNG)

![Java](https://img.shields.io/badge/Java-8%2B-blue?logo=java)
![Maven](https://img.shields.io/badge/Maven-3.6%2B-red?logo=apache-maven)
![TestNG](https://img.shields.io/badge/TestNG-7.7%2B-brightgreen)
![Build](https://img.shields.io/badge/Build-Passing-success)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 📌 Overview
This is a **Maven-based Test Automation Framework** built using **TestNG** for running automated tests.  
Currently, the framework supports **Web Automation** using Selenium WebDriver.  
It is designed to be **scalable** and **maintainable**, and will be extended to include **API Automation** in the near future.

---

## ✨ Features
- ✅ **Web Automation** with Selenium WebDriver
- ✅ **TestNG** for test execution and reporting
- ✅ **Maven** for dependency management
- ✅ **Page Object Model (POM)** for maintainability
- ✅ **Cross-browser testing** (Chrome, Firefox, Edge, etc.)
- ✅ **Configurable test data**(config.properties)
- ✅ **Parallel Execution** via testNg.xml 
- ✅ **HTML Reports** (TestNG, Extent Reports)
- 🔜 **API Automation** with REST Assured (Planned)
- 🔜 **CI/CD** with Jenkins (Planned)

---

## 🛠️ Tech Stack
| Component      | Technology |
|----------------|------------|
| **Language**   | Java       |
| **Build Tool** | Maven      |
| **Framework**  | TestNG     |
| **Automation** | Selenium WebDriver |
| **Logging**    | Log4j / SLF4J |
| **Reports**    | Extent Reports |

---

## ⚙️ Prerequisites
- Java **8** or higher
- Maven **3.6+**
- IDE (**Eclipse** / **IntelliJ IDEA** recommended)
- Browsers (**Chrome**, **Firefox**, **Edge**)

---

## 📥 Installation & Setup
```bash
# Clone the repository
git clone https://github.com/your-username/your-repo-name.git

# Navigate to project directory
cd your-repo-name

# Install dependencies
# mvn clean install
```
---

## ▶️ Running Tests
- **mvn test** (Run all tests)
- **mvn test -Dgroups=Sanity** (Run all tests with Sanity tag)
- **mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-suites/testng.parallel.xml** (Run TestNG suite in parallel mode)

---

## 📊 Reports
- Execution reports are generated in target/execution-reports/ folder

---

## 👨‍💻 Author
- Vijay Aher
- 📧 vijayaher0211@yahoo.com
- 🔗 www.linkedin.com/in/vijay-aher-qa





