# cdk-lambda-packaging-java

![Build status](https://github.com/aws-samples/cdk-lambda-packaging-java/actions/workflows/build.yml/badge.svg)

This sample application show how you can use [AWS Cloud Development Kit(AWS CDK)](https://aws.amazon.com/cdk/) to deploy a AWS lambda functions with 
external dependencies. [AWS Serverless Application Model (AWS SAM)](https://aws.amazon.com/serverless/sam/) takes care of building and packaging
lambda function with external dependencies out of the box. It was not possible this with AWS CDK until [s3-assets](https://mvnrepository.com/artifact/software.amazon.awscdk/s3-assets)
was introduced.

Assets are local files or directories which are needed by a CDK app. A common example is a directory which contains the handler code for a Lambda function, 
but assets can represent any artifact that is needed for the app's operation. When deploying a CDK app that includes constructs with assets, the CDK toolkit 
will first upload all the assets to S3, and only then deploy the stacks. The S3 locations of the uploaded assets will be passed in as CloudFormation Parameters 
to the relevant stacks.

When defining an asset, you can use the bundling option to specify a command to run inside a docker container. The command can read the contents of the asset 
source from /asset-input and is expected to write files under /asset-output (directories mapped inside the container). The files under /asset-output will be 
zipped and uploaded to S3 as the asset.

The `cdk.json` file inside `infrastructure` directory tells the CDK Toolkit how to execute your app.

It is a [Maven](https://maven.apache.org/) based project, so you can open this project with any Maven compatible Java IDE to build and run tests.

## Project structure
    
    ├── infrastructure          # Infrastructure code vi CDK(Java).
    ├── software                # Holds business logic in AWS lambda functions
    │   ├── FunctionOne         # Sample Lambda function  
    │   └── FunctionTwo         # Sample Lambda function
    └── ...

## Prerequisite

- Make sure you have [AWS CDK](https://docs.aws.amazon.com/cdk/latest/guide/getting_started.html) installed and configured with an aws account you want to use.
- Ensure you have [docker](https://docs.docker.com/get-docker/) installed and is up and running locally.

## Getting started

- Change directory to where infrastructure code lives.
```bash
    cd infrastructure
```

- Synthesize the cdk stack to emits the synthesized CloudFormation template. Set up will make sure to build and package 
  the lambda functions residing in [software](/software) directory. Build and packaging instruction for function resides in [InfrastructureStack](/infrastructure/src/main/java/com/myorg/InfrastructureStack.java)

```bash
    cdk synth
```

- Deploy the CDK application

```bash
    cdk deploy
```


## Useful commands

 * `mvn package`     compile and run tests
 * `cdk ls`          list all stacks in the app
 * `cdk synth`       emits the synthesized CloudFormation template
 * `cdk deploy`      deploy this stack to your default AWS account/region
 * `cdk diff`        compare deployed stack with current state
 * `cdk docs`        open CDK documentation

Enjoy!

## Security

See [CONTRIBUTING](CONTRIBUTING.md#security-issue-notifications) for more information.

## License

This library is licensed under the MIT-0 License. See the LICENSE file.
