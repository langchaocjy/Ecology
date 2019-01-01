package com.customization;

import java.util.List;

public class PageUtil<T>
{
  private int page = 1;
  public int totalPages = 0;
  private int pageRecorders;
  private int totalRows = 0;
  private int pageStartRow = 0;
  private int pageEndRow = 0;
  private boolean hasNextPage = false;
  private boolean hasPreviousPage = false;
  private List<T> list;
  
  public PageUtil(int page, int totalRows, int pageRecorders, List<T> list)
  {
    this.page = page;
    this.pageRecorders = pageRecorders;
    this.totalRows = totalRows;
    this.list = list;
    this.hasPreviousPage = false;
    if (totalRows % pageRecorders == 0) {
      this.totalPages = (totalRows / pageRecorders);
    } else {
      this.totalPages = (totalRows / pageRecorders + 1);
    }
    if (page >= this.totalPages) {
      this.hasNextPage = false;
    } else {
      this.hasNextPage = true;
    }
    if (page - 1 > 0) {
      this.hasPreviousPage = true;
    } else {
      this.hasPreviousPage = false;
    }
    if (page * pageRecorders < totalRows)
    {
      this.pageEndRow = (page * pageRecorders);
      this.pageStartRow = (this.pageEndRow - pageRecorders);
    }
    else
    {
      this.pageEndRow = totalRows;
      this.pageStartRow = (pageRecorders * (this.totalPages - 1));
    }
  }
  
  public String displayForPage(String method)
  {
    StringBuffer sb = new StringBuffer();
    sb.append("<div class=\"e8_pageinfo\"> ");
    if (isHasPreviousPage()) {
      sb.append("<a  class=\"e8_numberspan\"  href=\"javascript:void(0)\" onclick=\"" + method + "('" + String.valueOf(getPage() - 1) + "');return false;\"><</a>");
    }
    if ((isHasPreviousPage()) || (isHasNextPage())) {
      for (int i = 1; i <= getTotalPages(); i++)
      {
        String spanClzz = "";
        if (getTotalPages() > 7)
        {
          if (this.page <= 4)
          {
            spanClzz = "<a class=\"e8_numberspan\"  href=\"javascript:void(0)\" onclick=\"" + method + "('" + String.valueOf(i) + "');return false;\">" + i + "</a>";
            if (this.page == i) {
              spanClzz = "<span class=\"e8_numberspan\"  style=\"background-color: black;color: white;\">" + i + "</span>";
            }
            sb.append(spanClzz);
            if (i == 4)
            {
              if (this.page == 4)
              {
                i = 5;
                spanClzz = "<a class=\"e8_numberspan\"  href=\"javascript:void(0)\" onclick=\"" + method + "('" + String.valueOf(i) + "');return false;\">" + i + "</a>";
                sb.append(spanClzz);
              }
              i = getTotalPages() - 2;
              sb.append("<span class=\"e8_numberspan\" >. . .</span>");
            }
          }
          else if ((this.page > 4) && (this.page <= getTotalPages() - 4))
          {
            if (i < 3)
            {
              spanClzz = "<a class=\"e8_numberspan\"  href=\"javascript:void(0)\" onclick=\"" + method + "('" + String.valueOf(i) + "');return false;\">" + i + "</a>";
              sb.append(spanClzz);
            }
            if (this.page == i + 2)
            {
              sb.append("<span class=\"e8_numberspan\" >. . .</span>");
            }
            else if (this.page == i + 1)
            {
              spanClzz = "<a class=\"e8_numberspan\"  href=\"javascript:void(0)\" onclick=\"" + method + "('" + String.valueOf(i) + "');return false;\">" + i + "</a>";
              sb.append(spanClzz);
            }
            else if (this.page == i)
            {
              spanClzz = "<span class=\"e8_numberspan\"  style=\"background-color: black;color: white;\">" + i + "</span>";
              sb.append(spanClzz);
            }
            else if (this.page == i - 1)
            {
              spanClzz = "<a class=\"e8_numberspan\"  href=\"javascript:void(0)\" onclick=\"" + method + "('" + String.valueOf(i) + "');return false;\">" + i + "</a>";
              sb.append(spanClzz);
            }
            else if (this.page == i - 2)
            {
              sb.append("<span class=\"e8_numberspan\" >. . .</span>");
            }
            if (i > getTotalPages() - 2)
            {
              spanClzz = "<a class=\"e8_numberspan\"  href=\"javascript:void(0)\" onclick=\"" + method + "('" + String.valueOf(i) + "');return false;\">" + i + "</a>";
              sb.append(spanClzz);
            }
          }
          else if (this.page > getTotalPages() - 4)
          {
            if (i < 3)
            {
              spanClzz = "<a class=\"e8_numberspan\"  href=\"javascript:void(0)\" onclick=\"" + method + "('" + String.valueOf(i) + "');return false;\">" + i + "</a>";
              sb.append(spanClzz);
            }
            else if (i == 3)
            {
              i = getTotalPages() - 4;
              sb.append("<span class=\"e8_numberspan\" >. . .</span>");
              if (getTotalPages() - 3 == this.page)
              {
                spanClzz = "<a class=\"e8_numberspan\"  href=\"javascript:void(0)\" onclick=\"" + method + "('" + String.valueOf(i) + "');return false;\">" + i + "</a>";
                sb.append(spanClzz);
              }
            }
            else if (this.page == i)
            {
              spanClzz = "<span class=\"e8_numberspan\"  style=\"background-color: black;color: white;\">" + i + "</span>";
              sb.append(spanClzz);
            }
            else
            {
              spanClzz = "<a class=\"e8_numberspan\"  href=\"javascript:void(0)\" onclick=\"" + method + "('" + String.valueOf(i) + "');return false;\">" + i + "</a>";
              sb.append(spanClzz);
            }
          }
        }
        else
        {
          spanClzz = "<a class=\"e8_numberspan\"  href=\"javascript:void(0)\" onclick=\"" + method + "('" + String.valueOf(i) + "');return false;\">" + i + "</a>";
          if (this.page == i) {
            spanClzz = "<span class=\"e8_numberspan\"  style=\"background-color: black;color: white;\">" + i + "</span>";
          }
          sb.append(spanClzz);
        }
      }
    }
    if (isHasNextPage()) {
      sb.append("<a class=\"e8_numberspan\"  href=\"javascript:void(0)\" onclick=\"" + method + "('" + String.valueOf(getPage() + 1) + "');return false;\">></a>");
    }
    sb.append("<span style=\"height:30px;line-height:30px;color:#666666;\">第<input style=\"color:#666666;line-height:22px;text-align:center;border:0px;outline:none;\" type=\"text\" size=\"2\" name=\"page1\" id=\"page1\" value=" + 
      this.page + ">" + 
      
      "页&nbsp;&nbsp;</span>");
    sb.append("<input class=\"e8_btn_top_first\"   type=\"button\" value=\"跳转\"   onclick=\"" + method + "(document.getElementById('page1').value)\">");
    

    sb.append("<select name=\"pageRecorders\" id=\"pageRecorders\"  onchange=\"" + method + "(document.getElementById('page1').value)\"><option value=\"10\"  " + (
    		String.valueOf(this.pageRecorders).equals("10") ? "selected" : "") + "  >10</option><option value=\"20\"   " + (String.valueOf(this.pageRecorders).equals("20") ? "selected" : "") + 
      "  >20</option><option value=\"50\" " + (String.valueOf(this.pageRecorders).equals("50") ? "selected" : "") + " >50</option><option value=\"100\" " + (
      String.valueOf(this.pageRecorders).equals("100") ? "selected" : "") + " >100</option></select>");
    sb.append("<span style=\"height:30px;line-height:30px;color:#666666;\">&nbsp;&nbsp;条/页 | 共" + this.totalRows + "条&nbsp;&nbsp;</span>");
    sb.append("</div>");
    return sb.toString();
  }
  
  public List<T> getObjects(int page)
  {
    if (page == 0)
    {
      setPage(1);
      page = 1;
    }
    else
    {
      setPage(page);
    }
    if (page - 1 > 0) {
      this.hasPreviousPage = true;
    } else {
      this.hasPreviousPage = false;
    }
    if (page >= this.totalPages) {
      this.hasNextPage = false;
    } else {
      this.hasNextPage = true;
    }
    if (page * this.pageRecorders < this.totalRows)
    {
      this.pageEndRow = (page * this.pageRecorders);
      this.pageStartRow = (this.pageEndRow - this.pageRecorders);
    }
    else
    {
      this.pageEndRow = this.totalRows;
      this.pageStartRow = (this.pageRecorders * (this.totalPages - 1));
    }
    List<T> objects = null;
    if (!this.list.isEmpty()) {
      objects = this.list.subList(this.pageStartRow, this.pageEndRow);
    }
    return objects;
  }
  
  public List<T> getList()
  {
    return this.list;
  }
  
  public void setList(List<T> list)
  {
    this.list = list;
  }
  
  public int getPage()
  {
    return this.page;
  }
  
  public void setPage(int page)
  {
    this.page = page;
  }
  
  public int getTotalPages()
  {
    return this.totalPages;
  }
  
  public void setTotalPages(int totalPages)
  {
    this.totalPages = totalPages;
  }
  
  public int getPageRecorders()
  {
    return this.pageRecorders;
  }
  
  public void setPageRecorders(int pageRecorders)
  {
    this.pageRecorders = pageRecorders;
  }
  
  public int getTotalRows()
  {
    return this.totalRows;
  }
  
  public void setTotalRows(int totalRows)
  {
    this.totalRows = totalRows;
  }
  
  public int getPageStartRow()
  {
    return this.pageStartRow;
  }
  
  public void setPageStartRow(int pageStartRow)
  {
    this.pageStartRow = pageStartRow;
  }
  
  public int getPageEndRow()
  {
    return this.pageEndRow;
  }
  
  public void setPageEndRow(int pageEndRow)
  {
    this.pageEndRow = pageEndRow;
  }
  
  public boolean isHasNextPage()
  {
    return this.hasNextPage;
  }
  
  public void setHasNextPage(boolean hasNextPage)
  {
    this.hasNextPage = hasNextPage;
  }
  
  public boolean isHasPreviousPage()
  {
    return this.hasPreviousPage;
  }
  
  public void setHasPreviousPage(boolean hasPreviousPage)
  {
    this.hasPreviousPage = hasPreviousPage;
  }
}
