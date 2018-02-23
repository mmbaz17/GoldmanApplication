/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goldman.goldmanapplication;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.*;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

public class XmlReader {

    private static final Logger log = Logger.getLogger(XmlReader.class.getName());
    
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "");
            Statement st = con.createStatement();
            log.info("Connected to MySQL Server successfully");
            
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("c:\\goldman\\bookstore.xml"));
            
            doc.getDocumentElement().normalize();
            log.info("Root element of the doc is " + doc.getDocumentElement().getNodeName());
            NodeList listOfBooks = doc.getElementsByTagName("book");
            
            
            for (int s = 0; s < listOfBooks.getLength(); s++) {
                Node firstBookNode = listOfBooks.item(s);
                if (firstBookNode.getNodeType() == Node.ELEMENT_NODE) {
                    
                    Element firstBkElement = (Element) firstBookNode;
                    
                    NamedNodeMap catList = firstBkElement.getAttributes();
                    
                    Node nl =(Node) catList.getNamedItem("category");
                    String book_category = nl.getNodeValue().trim();
                    
                    NodeList titleList = firstBkElement.getElementsByTagName("title");
                    Element titleElement = (Element) titleList.item(0);
                    NodeList textTitList = titleElement.getChildNodes();
                    String title = ((Node) textTitList.item(0)).getNodeValue().trim();
                    
                    NodeList yearList = firstBkElement.getElementsByTagName("year");
                    Element yearElement = (Element) yearList.item(0);
                    NodeList yrList = yearElement.getChildNodes();
                    String year = ((Node) yrList.item(0)).getNodeValue().trim();
                    
                    NodeList priceList = firstBkElement.getElementsByTagName("price");
                    Element priceElement = (Element) priceList.item(0);
                    NodeList textPrList = priceElement.getChildNodes();
                    String price = ((Node) textPrList.item(0)).getNodeValue().trim();
                    
                    NodeList authorsList = firstBkElement.getElementsByTagName("author");
                    Element authElement = (Element) authorsList.item(0);
                    NodeList authList = authElement.getChildNodes();
                    String author = ((Node) authList.item(0)).getNodeValue().trim();
                    
                    int i = st.executeUpdate("insert into bookstore(book_category,title,year,price,author) values('" + book_category + "','" + title + "','" + year + "','" + price + "','" + author + "')");
                }
                else{
                    log.info("Something wrong");
                }
            }
            log.info("Data is successfully inserted!");
        } catch (IOException | ClassNotFoundException | SQLException | ParserConfigurationException | DOMException | SAXException err) {
            log.log(Level.INFO, " {0}", err.getMessage());
        }
    }
}
