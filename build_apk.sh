#! /bin/bash
WORKSPACE=$(cd $(dirname $0); pwd)
echo ${WORKSPACE}
cd ${WORKSPACE}

BUILD_TYPE=$1
BUILD_TIME=$2
PRODUCT_FLAVORS=$3
OUTPUT_PATH=$4

CODE_PATH="."
echo "build.sh BUILD_TYPE: ${BUILD_TYPE},BUILD_TIME: ${BUILD_TIME},PRODUCT_FLAVORS: ${PRODUCT_FLAVORS},OUTPUT_PATH: ${OUTPUT_PATH}"

echo =========build apk=========
chmod +x gradlew

TMP_PRODUCT_FLAVORS=$(echo ${PRODUCT_FLAVORS}|sed 's/^\w\|\s\w/\U&/g')

./gradlew clean assemble${TMP_PRODUCT_FLAVORS}${BUILD_TYPE}  --stacktrace

# 如果编译成功
if [ $? = 0 ]; then
    echo =========archive the artifacts=========
    DEPLOY_PATH=${OUTPUT_PATH}/konwledge/${PRODUCT_FLAVORS}/${BUILD_TIME}
	  echo "begin copy apk files: DEPLOY_PATH : ${DEPLOY_PATH}---OUTPUT_PATH: ${OUTPUT_PATH},BUILD_TIME: ${BUILD_TIME}"
	
	  # 创建文件夹
    if [ ! -d ${DEPLOY_PATH} ]; then
        mkdir -p ${DEPLOY_PATH}
    fi
	
	  echo "DEPLOY_PATH: ${DEPLOY_PATH}"
    echo "TMP_PRODUCT_FLAVORS: ${TMP_PRODUCT_FLAVORS}"
	
	  if [ ${BUILD_TYPE} = "Debug" ]; then
    	  # 拷贝文件到目录
    	  SOURCE_APK_FILE=${CODE_PATH}/app/build/outputs/apk/${PRODUCT_FLAVORS}/debug/app-${PRODUCT_FLAVORS}-debug.apk
    	  OUTPUT_APK_FILE=${DEPLOY_PATH}/KnowLedge_${TMP_PRODUCT_FLAVORS}_${BUILD_TIME}.apk

        cp ${SOURCE_APK_FILE} ${OUTPUT_APK_FILE}
    else
    	  # 拷贝文件到目录
    	  SOURCE_APK_FILE=${CODE_PATH}/app/build/outputs/apk/${PRODUCT_FLAVORS}/release/app-${PRODUCT_FLAVORS}-release.apk
    	  RENAME_APK=${CODE_PATH}/app/build/outputs/apk/${PRODUCT_FLAVORS}/release/KnowLedge_${TMP_PRODUCT_FLAVORS}_${BUILD_TIME}.apk
		  
		  echo "SOURCE_APK_FILE: ${SOURCE_APK_FILE}"
          echo "prepare copy source apk to : ${RENAME_APK}"
    	  cp ${SOURCE_APK_FILE} ${RENAME_APK}
		  
		  OUTPUT_APK_FILE=${DEPLOY_PATH}/KnowLedge_${TMP_PRODUCT_FLAVORS}_${BUILD_TIME}.apk
		  SOURCE_MAPPING_FILE=${CODE_PATH}/app/build/outputs/mapping/${PRODUCT_FLAVORS}Release/mapping.txt
    	  OUTPUT_MAPPING_FILE=${DEPLOY_PATH}/mapping.txt

          echo "copy source: ${SOURCE_APK_FILE} to ${OUTPUT_APK_FILE}"
		  cp ${SOURCE_APK_FILE} ${OUTPUT_APK_FILE}

		  echo "copy mapping : ${SOURCE_MAPPING_FILE} to ${OUTPUT_MAPPING_FILE}"
    	  cp ${SOURCE_MAPPING_FILE} ${OUTPUT_MAPPING_FILE}
    fi
	
	  LOG="BUILD_TYPE=${BUILD_TYPE}\nPRODUCT_FLAVORS=${PRODUCT_FLAVORS}\nOUTPUT_PATH=${OUTPUT_PATH}\nBUILD_TIME=${BUILD_TIME}\n"
    echo -e ${LOG} > ${DEPLOY_PATH}/log.txt
    LOG="./build_apk.sh BUILD_TYPE PRODUCT_FLAVORS OUTPUT_PATH BUILD_TIME "
    echo -e ${LOG} >> ${DEPLOY_PATH}/log.txt

    chmod -R 777 ${DEPLOY_PATH}
fi
		  
