/*
 *      Created By: ${USER}
 *      File Name: ${NAME}
 *      Project Name: ${PROJECT_NAME}
 *      Created on: ${DATE} ${TIME}
 *      Additional Notes: 
 */
 
 #if (${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

#if (${IMPORT_BLOCK} != "")${IMPORT_BLOCK}
#end
#parse("File Header.java")
#if (${VISIBILITY} == "PUBLIC")public #end enum ${NAME} #if (${INTERFACES} != "")implements ${INTERFACES} #end {
}
