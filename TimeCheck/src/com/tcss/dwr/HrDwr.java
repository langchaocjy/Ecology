package com.tcss.dwr;

import com.tcss.util.TcssUtil;
import java.io.PrintStream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import out.deal.afs.kq003.DT_OA_AFS_OA003;
import out.deal.afs.kq003.DT_OA_AFS_OA003_REP;
import out.deal.afs.kq003.MT_OA_AFS_OA003;
import out.deal.afs.kq003.MT_OA_AFS_OA003_REP;
import out.deal.afs.kq003.SIO_OA_AFS_OA003Service;
import out.deal.afs.kq003.SIO_OA_AFS_OA003ServiceStub;
import out.deal.afs.kq004.DT_OA_AFS_OA004;
import out.deal.afs.kq004.DT_OA_AFS_OA004_REP;
import out.deal.afs.kq004.MT_OA_AFS_OA004;
import out.deal.afs.kq004.MT_OA_AFS_OA004_REP;
import out.deal.afs.kq004.RETURN_type0;
import out.deal.afs.kq004.SIO_OA_AFS_OA004Service;
import out.deal.afs.kq004.SIO_OA_AFS_OA004ServiceStub;
import out.deal.afs.kq004.Table31_dt1_type0;
import out.deal.afs.kq007.DT_OA_AFS_OA007;
import out.deal.afs.kq007.DT_OA_AFS_OA007_REP;
import out.deal.afs.kq007.MT_OA_AFS_OA007;
import out.deal.afs.kq007.MT_OA_AFS_OA007_REP;
import out.deal.afs.kq007.RESLUT_type0;
import out.deal.afs.kq007.SIO_OA_AFS_OA007Service;
import out.deal.afs.kq007.SIO_OA_AFS_OA007ServiceStub;
import out.deal.afs.kq007.Ygh_table_type0;
import weaver.conn.RecordSet;

public class HrDwr
  extends TcssUtil
{
  public static void main(String[] args)
  {
    System.out.println("2017-01-01".substring(0, 7));
  }
  
  public JSONObject getKxnj(String userid, String rq)
  {
    JSONObject json = new JSONObject();
    JSONObject user = getObjectById(userid, "hrmresource", "id");
    if (user.has("workcode"))
    {
      String workcode = user.getString("workcode");
      try
      {
        SIO_OA_AFS_OA003Service stub = new SIO_OA_AFS_OA003ServiceStub();
        MT_OA_AFS_OA003 mT_OA_AFS_OA003 = new MT_OA_AFS_OA003();
        DT_OA_AFS_OA003 param = new DT_OA_AFS_OA003();
        param.setGh(workcode);
        param.setSqrq(getSapDate(rq));
        mT_OA_AFS_OA003.setMT_OA_AFS_OA003(param);
        MT_OA_AFS_OA003_REP sIO_OA_AFS_OA003 = stub.sIO_OA_AFS_OA003(mT_OA_AFS_OA003);
        DT_OA_AFS_OA003_REP mt_OA_AFS_OA003_REP = sIO_OA_AFS_OA003.getMT_OA_AFS_OA003_REP();
        String fdzj = mt_OA_AFS_OA003_REP.getFdzj();
        String kxnj = mt_OA_AFS_OA003_REP.getKxnj();
        String kxtx = mt_OA_AFS_OA003_REP.getKxtx();
        
        String qnnjss = mt_OA_AFS_OA003_REP.getQnnjss();
        String jnnjss = mt_OA_AFS_OA003_REP.getJnnjss();
        String qntxss = mt_OA_AFS_OA003_REP.getQntxss();
        String jntxss = mt_OA_AFS_OA003_REP.getJntxss();
        
        write("rq:" + rq + ",userid:" + userid + ",workcode:" + workcode + ", fdzj:" + fdzj + ",kxnj:" + kxnj + ",kxtx:" + kxtx);
        write("rq:" + rq + ",userid:" + userid + ",workcode:" + workcode + ", qnnjss:" + qnnjss + ",jnnjss:" + jnnjss + ",qntxss:" + qntxss + ",jntxss:" + jntxss);
        json.put("fdzj", fdzj);
        json.put("kxnj", kxnj);
        json.put("kxtx", kxtx);
        
        json.put("qnnjss", qnnjss);
        json.put("jnnjss", jnnjss);
        json.put("qntxss", qntxss);
        json.put("jntxss", jntxss);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      json.put("fdzj", "0");
      json.put("kxnj", "0");
      json.put("kxtx", "0");
    }
    return json;
  }
  
  public JSONObject getQjxsh(String userid, String begindate, String begintime, String enddate, String endtime, String qjlx)
  {
    JSONObject json = new JSONObject();
    JSONObject user = getObjectById(userid, "hrmresource", "id");
    if (user.has("workcode"))
    {
      String workcode = user.getString("workcode");
      write("getSapDate(begindate):" + getSapDate(begindate) + ",getSapTime(begintime)" + getSapTime(begintime) + ",getSapDate(enddate):" + getSapDate(enddate) + ",getSapTime(endtime):" + 
        getSapTime(endtime) + ",qjlx:" + qjlx);
      try
      {
        SIO_OA_AFS_OA004Service stub = new SIO_OA_AFS_OA004ServiceStub();
        MT_OA_AFS_OA004 mT_OA_AFS_OA004 = new MT_OA_AFS_OA004();
        DT_OA_AFS_OA004 param = new DT_OA_AFS_OA004();
        param.setGH(workcode);
        Table31_dt1_type0[] t31 = new Table31_dt1_type0[1];
        for (int i = 0; i < t31.length; i++)
        {
          t31[i] = new Table31_dt1_type0();
          t31[i].setQjksrq(getSapDate(begindate));
          t31[i].setQjkssj(getSapTime(begintime));
          t31[i].setQjjsrq(getSapDate(enddate));
          t31[i].setQjjssj(getSapTime(endtime));
          t31[i].setQjlx(qjlx);
        }
        param.setTable31_dt1(t31);
        mT_OA_AFS_OA004.setMT_OA_AFS_OA004(param);
        MT_OA_AFS_OA004_REP sIO_OA_AFS_OA004 = stub.sIO_OA_AFS_OA004(mT_OA_AFS_OA004);
        DT_OA_AFS_OA004_REP mt_OA_AFS_OA004_REP = sIO_OA_AFS_OA004.getMT_OA_AFS_OA004_REP();
        RETURN_type0[] return1 = mt_OA_AFS_OA004_REP.getRETURN();
        if (return1 != null)
        {
          if (return1.length > 0)
          {
            RETURN_type0 return_type0 = return1[0];
            String hjts = return_type0.getHjts();
            String hjsc = return_type0.getHjsc();
            json.put("hjts", hjts);
            json.put("hjsc", hjsc);
            write("gh:" + workcode + ",hjts=" + hjts + ",hjsc:" + hjsc + ",qjlx:" + qjlx);
          }
          else
          {
            json.put("hjts", "0");
            json.put("hjsc", "0");
          }
        }
        else
        {
          json.put("hjts", "0");
          json.put("hjsc", "0");
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      json.put("hjts", "0");
      json.put("hjsc", "0");
    }
    return json;
  }
  
  public double getYjbxss(String userid, String rq)
  {
    double yjbxss = 0.0D;
    JSONObject user = getObjectById(userid, "hrmresource", "id");
    if (user.has("workcode"))
    {
      String workcode = user.getString("workcode");
      write("userid:" + userid + ",rq:" + rq + ",workcode:" + workcode);
      try
      {
        SIO_OA_AFS_OA007Service stub = new SIO_OA_AFS_OA007ServiceStub();
        MT_OA_AFS_OA007 mT_OA_AFS_OA007 = new MT_OA_AFS_OA007();
        DT_OA_AFS_OA007 param = new DT_OA_AFS_OA007();
        param.setSqrq(getSapDate(rq));
        Ygh_table_type0[] ytt = new Ygh_table_type0[1];
        for (int i = 0; i < ytt.length; i++)
        {
          ytt[i] = new Ygh_table_type0();
          ytt[i].setGh(workcode);
        }
        param.setYgh_table(ytt);
        mT_OA_AFS_OA007.setMT_OA_AFS_OA007(param);
        MT_OA_AFS_OA007_REP sIO_OA_AFS_OA007 = stub.sIO_OA_AFS_OA007(mT_OA_AFS_OA007);
        DT_OA_AFS_OA007_REP mt_OA_AFS_OA007_REP = sIO_OA_AFS_OA007.getMT_OA_AFS_OA007_REP();
        RESLUT_type0[] reslut = mt_OA_AFS_OA007_REP.getRESLUT();
        if ((reslut != null) && 
          (reslut.length > 0))
        {
          RESLUT_type0 reslut_type0 = reslut[0];
          yjbxss = Double.parseDouble(reslut_type0.getYjbss());
          write("yjbxss:" + yjbxss);
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return yjbxss;
  }
  
  public double getJbxshByOa(String sqr, String jbrq)
  {
    double jbxsh = 0.0D;
    RecordSet rs = new RecordSet();
    String sql = "select nvl(sum(jbxss),0) jbxss from (select jbxss from formtable_main_32_dt1 where mainid in(select id from formtable_main_32 where xm='" + 
      sqr + 
      "'  and requestid in(select requestid from workflow_requestbase where currentnodetype in (1,2))) and substr(jbksrq,0,7)='" + 
      jbrq.substring(0, 7) + 
      "' UNION all select bcjbss from formtable_main_37_dt1 where mainid in(select id from formtable_main_37 where  requestid in(select requestid from workflow_requestbase where currentnodetype in (1,2)) and substr(jbksrq,0,7)='" + 
      jbrq.substring(0, 7) + "' ) and xm='" + sqr + "'  )";
    write("获取在途审批的加班小时数sql:" + sql);
    rs.execute(sql);
    if (rs.next()) {
      jbxsh = rs.getDouble("jbxss");
    }
    return jbxsh;
  }
  
  public JSONArray getQjData(String qjlc)
  {
    JSONArray jsons = new JSONArray();
    String sql = "select * from formtable_main_31_dt1 where mainid in(select id from formtable_main_31 where requestid ='" + qjlc + "' ) ";
    write("获取请假明细sql:" + sql);
    RecordSet rs = new RecordSet();
    rs.execute(sql);
    while (rs.next())
    {
      JSONObject json = new JSONObject();
      json.put("请假类型", null2String(rs.getString("qjlx")));
      json.put("请假类型名称", getObjectById(null2String(rs.getString("qjlx")), "uf_qjlxdb", "bianma").getString("qjlx"));
      json.put("请假开始日期", null2String(rs.getString("qjksrq")));
      json.put("请假开始时间", null2String(rs.getString("qjkssj")));
      json.put("请假结束日期", null2String(rs.getString("qjjsrq")));
      json.put("请假结束时间", null2String(rs.getString("qjjssj")));
      json.put("合计天数", null2String(rs.getString("hjts")));
      json.put("合计时长", null2String(rs.getString("hjsc")));
      json.put("相关证明文件", null2String(rs.getString("xgzmwj")));
      jsons.add(json);
    }
    return jsons;
  }
}
