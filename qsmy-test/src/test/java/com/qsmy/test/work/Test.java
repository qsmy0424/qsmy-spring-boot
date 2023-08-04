package com.qsmy.test.work;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qsmy
 * @time 2023-04-26
 */
public class Test {
    public static void main(String[] args) {
        findUserListByKeyWord("414012101", "田", 0, 0);
    }


    public static void findUserListByKeyWord(String areaCode, String keyword, Integer pageIndex, Integer pageSize){
        String sql="SELECT t.user_id \"userId\"," +
                " t.phone \"phone\", " +
                " t.APP_LAST_LOGIN \"lastLogin\", " +
                " t.real_name \"userName\", " +
                " t.orderno, " +
                " nvl(dept.oms_dept_code,omsDept.oms_dept_code) as oms_dept_code" +
                " from app_user t" +
                " left join APP_DEPT_NEW dept on (t.bz_id = dept.rz_id OR (t.dept_id = dept.rz_id and t.bz_id is null)) " +
                " left join EMS_ZSTJ_OMS_DEPT_APP omsDept on (t.bz_id = omsDept.rz_id OR (t.dept_id = omsDept.rz_id and t.bz_id is null)) " +
                " where 1=1 and deleted='0' AND user_type IN ('0','2')";
        MapSqlParameterSource params= new MapSqlParameterSource();
        if("41000".equals(areaCode) || StringUtils.isEmpty(areaCode)){
        }else{
            if ("3540000".equals(areaCode))
            {
                //本部处理
                sql+=" and t.app_unitcode='13'";
            }
            else if (areaCode.length()<=7)
            {
                //单位一级查看
                sql+=" and t.app_unitCode in (select gdprs_dept_code from ems_zstj_oms_dept_app where oms_dept_code like :areaCode) ";
                params.addValue("areaCode",areaCode+"%");
            }
            else
            {
                //部门里查看 用union代替OR
                params.addValue("areaCode",areaCode);
                sql  = sql + " and (t.bz_id in (select rz_id from app_dept_new where oms_dept_code like :areaCode)) union "
                        + sql + " and t.dept_id in (select rz_id from app_dept_new where oms_dept_code like :areaCode) and t.bz_id is null ";
                params.addValue("areaCode",areaCode+"%");
            }
        }
        if (StringUtils.isNotEmpty(keyword))
        {
            sql+=" and (instr(t.dept_name_path,:keyword) > 0 or  instr(t.real_name,:keyword) > 0)";
            params.addValue("keyword",keyword);
        }

        sql+=" ORDER BY ORDERNO ASC nulls last";
        sql = sql.replace(":areaCode", "'414012101%'");
        sql = sql.replace(":keyword", "'田'");
        System.out.println(sql);
    }
}


