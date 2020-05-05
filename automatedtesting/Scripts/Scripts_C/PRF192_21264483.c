/*
START_POINT_ARRtestcase:1END_POINT_ARR
 */

#include <stdio.h>
#include <stdlib.h>
#include <CUnit/Basic.h>
#include <CUnit/TestRun.h>
#include <CUnit/Automated.h>


#include "student/TemplateQuestion.c"

/*
 * 
 */
int init_suite(void) {
    return 0;
}

int clean_suite(void) {
    return 0;
}

        //start
 
public void testcase(){driver.findElement(By.id($paramName)).clear(); driver.findElement(By.id($paramName)).sendKeys($paramValue);}
//end

int main() {
    CU_pSuite pSuite = NULL;
    /* Initialize the CUnit test registry */
    if (CUE_SUCCESS != CU_initialize_registry())
        return CU_get_error();

    /* Add a suite to the registry */
    pSuite = CU_add_suite("Test script", init_suite, clean_suite);
    if (NULL == pSuite) {
        CU_cleanup_registry();
        return CU_get_error();
    }

    /* Add the tests to the suite */
    if ((NULL == CU_add_test(pSuite, "script_checkBubbleSort", script_checkBubbleSort))
            || (NULL == CU_add_test(pSuite, "script_checkLeapYear", script_checkLeapYear))
            || (NULL == CU_add_test(pSuite, "script_findTheLargestNumInArray", script_findTheLargestNumInArray))
            
            ) {
        CU_cleanup_registry();
        return CU_get_error();
    }

    /* Run all tests using the CUnit Basic interface */
    CU_basic_set_mode(CU_BRM_VERBOSE);
    CU_basic_run_tests();
    CU_automated_run_tests();
    CU_list_tests_to_file();

    CU_cleanup_registry();

    return CU_get_error();
}

