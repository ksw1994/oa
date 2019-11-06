package com.bootdo.system.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.PersonDO;
import com.bootdo.system.service.PersonService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-09 14:36:01
 */

@Controller
@RequestMapping("/system/person")
public class PersonController {


    @Autowired
    private BootdoConfig bootdoConfig;

    @Autowired
    private PersonService personService;

    @GetMapping()
    @RequiresPermissions("system:person:person")
    String Person() {
        return "system/person/person";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:person:person")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<PersonDO> personList = personService.list(query);
        int total = personService.count(query);
        PageUtils pageUtils = new PageUtils(personList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("system:person:add")
    String add() {
        return "system/person/add";
    }

    @GetMapping("/edit/{pid}")
    @RequiresPermissions("system:person:edit")
    String edit(@PathVariable("pid") Integer pid, Model model) {
        PersonDO person = personService.get(pid);
        model.addAttribute("person", person);
        return "system/person/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:person:add")
    public R save(PersonDO person) {
        if (personService.save(person) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:person:edit")
    public R update(PersonDO person) {
        personService.update(person);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:person:remove")
    public R remove(Integer pid) {
        if (personService.remove(pid) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:person:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] pids) {
        personService.batchRemove(pids);
        return R.ok();
    }

    @ResponseBody
    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file, @RequestParam Map param, HttpServletRequest request) {

        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        PersonDO pers = new PersonDO();
        pers.setName((String) param.get("name"));
        pers.setAddress((String) param.get("address"));
        pers.setPhoto("/files/" + fileName);


        try {
            FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            return R.error();
        }

        if (personService.save(pers) > 0) {

            return R.ok().put("fileName", pers.getPhoto());
        }

        return R.error();

    }

    @ResponseBody
    @PostMapping("/uploadImg")
    public R uploadImg(@RequestParam("file") MultipartFile file,PersonDO  person, HttpServletRequest request) {

        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);

        person.setPhoto("/files/" + fileName);

        try {
            FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            return R.error();
        }


            return R.ok().put("fileName", person.getPhoto());

    }


}
