
# HyperledgerFabricSample
From ellscha
0.6 ~ SDK 

First thing you will need to do to make this project work for you is to either sign in or sign up to [IBM Bluemix](https://console.ng.bluemix.net/). Once you are signed in, you will want to choose the option to creat an app and then search by keyword for 'blockchain'. 

Select the blockchain option and follow the steps until you can launch your dashboard. 

Clone the project.

Next, you will need to add a file into the Resources folder and call it `config.properties`. Then paste the following snippet into the file and change the ######## into your personal bluemix account secrets.

```

com.atraurablockchain.chainid = testchainid
com.atraurablockchain.chaincodename = mycc
com.atraurablockchain.chaincodepath = github.com/example_ccffff
com.atraurablockchain.ordereraddress = grpc://localhost:7050
com.atraurablockchain.peeraddress = ########
com.atraurablockchain.eventhubaddress = grpc://localhost:7053
com.atraurablockchain.caaddress = http://localhost:7054
com.atraurablockchain.username_1 = user_type2_2
com.atraurablockchain.userpswd_1 = #########
com.atraurablockchain.username_2 = user_type1_2
com.atraurablockchain.userpswd_2 = #########
com.atraurablockchain.keystore_properties = keystore.properties
com.atraurablockchain.invoke_waittime = 1000
com.atraurablockchain.deploy_waittime = 20000
com.atraurablockchain.deploy_function = init
com.atraurablockchain.deploy_args = a,100,b,200
com.atraurablockchain.invoke_function = invoke
com.atraurablockchain.invoke_args = move,a,b,100
com.atraurablockchain.query_args = query,b
```

Now you should be able to run the code.

