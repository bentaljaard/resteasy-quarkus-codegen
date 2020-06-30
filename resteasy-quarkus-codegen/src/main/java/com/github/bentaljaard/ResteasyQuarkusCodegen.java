package com.github.bentaljaard;

import org.openapitools.codegen.CodegenModel;
import org.openapitools.codegen.SupportingFile;
import org.openapitools.codegen.languages.JavaResteasyServerCodegen;

import io.swagger.v3.oas.models.media.Schema;

/**
 * Hello world!
 *
 */
public class ResteasyQuarkusCodegen extends JavaResteasyServerCodegen
{
    @Override
    public void processOpts() {
        super.processOpts();
        

        supportingFiles.clear();
        
        supportingFiles.add(new SupportingFile("ApiException.mustache",
                (sourceFolder + '/' + apiPackage).replace(".", "/"), "ApiException.java"));
        supportingFiles.add(new SupportingFile("ApiOriginFilter.mustache",
                (sourceFolder + '/' + apiPackage).replace(".", "/"), "ApiOriginFilter.java"));
        supportingFiles.add(new SupportingFile("ApiResponseMessage.mustache",
                (sourceFolder + '/' + apiPackage).replace(".", "/"), "ApiResponseMessage.java"));
        supportingFiles.add(new SupportingFile("NotFoundException.mustache",
                (sourceFolder + '/' + apiPackage).replace(".", "/"), "NotFoundException.java"));

        supportingFiles.add(new SupportingFile("ReadinessCheckImpl.mustache",
                (implFolder + '/' + apiPackage + "/impl").replace(".", "/"), "ReadinessCheckImpl.java"));
        supportingFiles.add(new SupportingFile("LivenessCheckImpl.mustache",
                (implFolder + '/' + apiPackage + "/impl").replace(".", "/"), "LivenessCheckImpl.java"));
        

        supportingFiles.add(new SupportingFile("StringUtil.mustache",
                (sourceFolder + '/' + invokerPackage).replace(".", "/"), "StringUtil.java"));
        supportingFiles.add(new SupportingFile("JacksonConfig.mustache",
                (sourceFolder + '/' + invokerPackage).replace(".", "/"), "JacksonConfig.java"));
        supportingFiles.add(new SupportingFile("RFC3339DateFormat.mustache",
                (sourceFolder + '/' + invokerPackage).replace(".", "/"), "RFC3339DateFormat.java"));

        if ("joda".equals(dateLibrary)) {
            supportingFiles.add(new SupportingFile("JodaDateTimeProvider.mustache",
                    (sourceFolder + '/' + apiPackage).replace(".", "/"), "JodaDateTimeProvider.java"));
            supportingFiles.add(new SupportingFile("JodaLocalDateProvider.mustache",
                    (sourceFolder + '/' + apiPackage).replace(".", "/"), "JodaLocalDateProvider.java"));
        } else if (dateLibrary.startsWith("java8")) {
            supportingFiles.add(new SupportingFile("OffsetDateTimeProvider.mustache",
                    (sourceFolder + '/' + apiPackage).replace(".", "/"), "OffsetDateTimeProvider.java"));
            supportingFiles.add(new SupportingFile("LocalDateProvider.mustache",
                    (sourceFolder + '/' + apiPackage).replace(".", "/"), "LocalDateProvider.java"));
        }


        
        // openApiSpecFileLocation = "src/main/resources/META-INF/openapi.yaml";
        // if (StringUtils.isNotEmpty(openApiSpecFileLocation)) {
        //     int index = openApiSpecFileLocation.lastIndexOf('/');
        //     String fileFolder;
        //     String fileName;
        //     if (index >= 0) {
        //         fileFolder = openApiSpecFileLocation.substring(0, index);
        //         fileName = openApiSpecFileLocation.substring(index + 1);
        //     } else {
        //         fileFolder = "";
        //         fileName = openApiSpecFileLocation;
        //     }
        //     supportingFiles.add(new SupportingFile("openapi.mustache", fileFolder, fileName));
        // }


    }
    


    @Override
    public CodegenModel fromModel(String name, Schema model) {
        CodegenModel codegenModel = super.fromModel(name, model);

        codegenModel.imports.remove("ApiModelProperty");
        codegenModel.imports.remove("ApiModel");

        return codegenModel;
    }
}
