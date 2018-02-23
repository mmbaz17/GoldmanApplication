/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goldman.goldmanapplication;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(catalog = "mysql", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bookstore.findAll", query = "SELECT b FROM Bookstore b")
    , @NamedQuery(name = "Bookstore.findByTitle", query = "SELECT b FROM Bookstore b WHERE b.title = :title")
    , @NamedQuery(name = "Bookstore.findByYear", query = "SELECT b FROM Bookstore b WHERE b.year = :year")
    , @NamedQuery(name = "Bookstore.findByPrice", query = "SELECT b FROM Bookstore b WHERE b.price = :price")
    , @NamedQuery(name = "Bookstore.findByAuthor", query = "SELECT b FROM Bookstore b WHERE b.author = :author")
    , @NamedQuery(name = "Bookstore.findByBookCategory", query = "SELECT b FROM Bookstore b WHERE b.bookCategory = :bookCategory")})
public class Bookstore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    private String title;
    private Integer year;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Float price;
    @Size(max = 50)
    private String author;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "book_category")
    private String bookCategory;

    public Bookstore() {
    }

    public Bookstore(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public Bookstore(String bookCategory, String title) {
        this.bookCategory = bookCategory;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookCategory != null ? bookCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bookstore)) {
            return false;
        }
        Bookstore other = (Bookstore) object;
        if ((this.bookCategory == null && other.bookCategory != null) || (this.bookCategory != null && !this.bookCategory.equals(other.bookCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.goldman.goldmanapplication.Bookstore[ bookCategory=" + bookCategory + " ]";
    }
    
}
