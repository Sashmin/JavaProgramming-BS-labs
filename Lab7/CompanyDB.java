
import javax.xml.transform.TransformerException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CompanyDB {
    ArrayList<Company> dataBase = new ArrayList<>();

    public CompanyDB() {}
    public CompanyDB(String file) throws Exception {
        Scanner fileScan = new Scanner(new File(file));
        while (fileScan.hasNextLine()) {
            dataBase.add(new Company(fileScan.nextLine()));
        }
    }

    public CompanyDB(CompanyDB other) {
        dataBase = new ArrayList<>();
        for (Company company: other.dataBase) {
            dataBase.add(new Company(company));
        }
    }

    @Override
    public String toString() {
        return "CompanyDB{" +
                "dataBase=" + dataBase +
                '}';
    }

    public void createXml(String xmlName) throws ParserConfigurationException, TransformerException, Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element rootElem = doc.createElement("companies");
        doc.appendChild(rootElem);

        for (Company company: dataBase) {
            Element curElem = doc.createElement("company");
            rootElem.appendChild(curElem);

            Element elemField = doc.createElement("name");
            elemField.setTextContent(company.getName());
            curElem.appendChild(elemField);

            elemField = doc.createElement("shortTitle");
            elemField.setTextContent(company.getShortTitle());
            curElem.appendChild(elemField);

            elemField = doc.createElement("dateUpdate");
            if (company.getDateUpdate() != null) {
                elemField.setTextContent(company.getDateUpdate().toString());
            }
            curElem.appendChild(elemField);

            elemField = doc.createElement("address");
            elemField.setTextContent(company.getAddress());
            curElem.appendChild(elemField);

            elemField = doc.createElement("dateFoundation");
            elemField.setTextContent(company.getDateFoundation().toString());
            curElem.appendChild(elemField);

            elemField = doc.createElement("countEmployees");
            elemField.setTextContent(String.valueOf(company.getCountEmployees()));
            curElem.appendChild(elemField);

            elemField = doc.createElement("auditor");
            elemField.setTextContent(company.getAuditor());
            curElem.appendChild(elemField);

            elemField = doc.createElement("phone");
            elemField.setTextContent(company.getPhone());
            curElem.appendChild(elemField);

            elemField = doc.createElement("email");
            elemField.setTextContent(company.getEmail());
            curElem.appendChild(elemField);

            elemField = doc.createElement("branch");
            elemField.setTextContent(company.getBranch());
            curElem.appendChild(elemField);

            elemField = doc.createElement("activity");
            elemField.setTextContent(company.getActivity());
            curElem.appendChild(elemField);

            elemField = doc.createElement("link");
            elemField.setTextContent(company.getLink());
            curElem.appendChild(elemField);
        }

        try (FileOutputStream output =
                     new FileOutputStream(xmlName)) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeXml(Document doc,
                                 OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }

    private CompanyDB findByShortTitle(String shortName) {
        CompanyDB tempDB = new CompanyDB();
        for (Company company: dataBase) {
            if (company.getShortTitle().equals(shortName)) {
                tempDB.dataBase.add(new Company(company));
            }
        }
        return tempDB;
    }

    private CompanyDB findByBranch(String branch) {
        CompanyDB tempDB = new CompanyDB();
        for (Company company: dataBase) {
            if (company.getBranch().equals(branch)) {
                tempDB.dataBase.add(new Company(company));
            }
        }
        return tempDB;
    }

    private CompanyDB findByActivity(String activity) {
        CompanyDB tempDB = new CompanyDB();
        for (Company company: dataBase) {
            if (company.getActivity().equals(activity)) {
                tempDB.dataBase.add(new Company(company));
            }
        }
        return tempDB;
    }

    private CompanyDB findByFoundationDateRange(String from, String to) throws Exception{
        Company.MyDate fromDate = new Company.MyDate(from);
        Company.MyDate toDate = new Company.MyDate(to);
        CompanyDB tempDB = new CompanyDB();
        for (Company company: dataBase) {
            if (company.getDateFoundation().compareTo(fromDate) >= 0
                && company.getDateFoundation().compareTo(toDate) <= 0) {
                tempDB.dataBase.add(company);
            }
        }
        return tempDB;
    }

    private CompanyDB findByCountEmployees(int from, int to) throws Exception{
        CompanyDB tempDB = new CompanyDB();
        for (Company company: dataBase) {
            if (company.getCountEmployees() >= from
                && company.getCountEmployees() <= to) {
                tempDB.dataBase.add(company);
            }
        }
        return tempDB;
    }

    public static void main(String[] args) throws Exception{
        CompanyDB db = new CompanyDB("book_new.csv");
        db.createXml("initial.xml");
        db.findByFoundationDateRange("06.05.98", "05.09.2000").createXml("result1.xml");
        db.findByBranch("smartphones").createXml("result2.xml");
        db.findByCountEmployees(200, 1000).createXml("result3.xml");
    }
}
