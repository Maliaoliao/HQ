package com.hq.system.service.impl;

import java.util.List;
                import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
    import com.hq.system.mapper.TestMapper;
import com.hq.system.domain.Test;
import com.hq.system.service.ITestService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author wxy
 * @date 2021-10-28
 */
@Service
public class TestServiceImpl implements ITestService {
    @Autowired
    private TestMapper testMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param test 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public Test selectTestById(Long test) {
        return testMapper.selectTestById(test);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param test 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Test> selectTestList(Test test) {
        return testMapper.selectTestList(test);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param test 【请填写功能名称】
     * @return 结果
     */
            @Override
    public int insertTest(Test test) {
                                                return testMapper.insertTest(test);
            }

    /**
     * 修改【请填写功能名称】
     *
     * @param test 【请填写功能名称】
     * @return 结果
     */
            @Override
    public int updateTest(Test test) {
                                            return testMapper.updateTest(test);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param tests 需要删除的【请填写功能名称】ID
     * @return 结果
     */
            @Override
    public int deleteTestByIds(Long[] tests) {
                return testMapper.deleteTestByIds(tests);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param test 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTestById(Long test) {
                return testMapper.deleteTestById(test);
    }
    }
