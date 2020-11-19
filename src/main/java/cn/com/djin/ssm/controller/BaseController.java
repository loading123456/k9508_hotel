package cn.com.djin.ssm.controller;

import cn.com.djin.ssm.service.AuthService;
import cn.com.djin.ssm.service.BaseService;
import cn.com.djin.ssm.service.OrdersService;
import cn.com.djin.ssm.service.RoomSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   公共控制器层对象
 * @param <T>  泛指实体对象类型
 */
public class BaseController<T> {

    //依赖公共的业务层对象
    @Autowired
    protected BaseService<T> baseService;

    //依赖订单业务层对象
    @Autowired
    protected OrdersService ordersService;

    //依赖权限业务层对象
    @Autowired
    protected AuthService authService;

    //房间销售记录的业务层对象
    @Autowired
    protected RoomSaleService roomSaleService;

    /**
     *    根据条件分页加载数据
     * @param page  当前页
     * @param limit  每一页的数据条数
     * @param t   查询的条件
     * @return   分页结果集Map集合
     */
    @RequestMapping("/loadPageTByPramas")
    public @ResponseBody Map<String,Object> loadPageTByPramas(Integer page, Integer limit, T t){
        System.out.println(t);
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            map = baseService.findPageTByPramas(page,limit,t);
            map.put("code",0);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",200);
        }
        return map;
    }

    /**
     *    根据主键id进行数据的动态修改
     * @param t  传入的要修改参数数据实体对象
     * @return   修改的结果
     */
    @RequestMapping("/updByPrimaryKeySelective")
    public @ResponseBody String updByPrimaryKeySelective(T t){
        try {
            return baseService.updByPrimaryKeySelective(t);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *    批量修改数据
     * @param ids  主键数组
     * @param t   要修改的数据实体对象
     * @return   修改的结果
     */
    @RequestMapping("/updBatchByPrimaryKeySelective")
    public @ResponseBody String updBatchByPrimaryKeySelective(Integer[]ids,T t){
        try {
            return baseService.updBatchByPrimaryKeySelective(ids,t);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *    根据条件查询单个数据
     * @param t  传入的查询条件
     * @return  返回查询的数据对象
     */
    @RequestMapping("/loadTByPramas")
    public @ResponseBody T loadTByPramas(T t){
        try {
            return baseService.findTByPramas(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *    添加数据
     * @param t  要添加的数据的对象
     * @return  添加结果
     * @throws Exception
     */
    @RequestMapping("/saveT")
    public @ResponseBody String saveT(T t){
        try {
            return baseService.saveT(t);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *     根据条件查询多个数据
     * @param t  传入的查询条件
     * @return   多个数据结果
     * @throws Exception
     */
    @RequestMapping("/loadManyTByPramas")
    public @ResponseBody List<T> loadManyTByPramas(T t){
        try {
            return baseService.findManyTByPramas(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *   查询所有数据
     * @return  查询的数据对象结果集
     * @throws Exception
     */
    @RequestMapping("/loadAllT")
    public @ResponseBody List<T> loadAllT(){
        try {
            return baseService.findAllT();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //根据条件查询数据条数
    @RequestMapping("/getCountByPramas")
    public @ResponseBody Integer getCountByPramas(T t){
        try {
            return baseService.getCountByPramas(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




}
