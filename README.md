# activiti-mqtt

This project aims to create a basic gateway between Activiti and an MQTT broker.  There are a few goals:

1.  Be able to start a workflow from a message sent to an MQTT broker
2.  Be able to publish messages back to an MQTT broker from an Activiti task
3.  Be able to affect the state of Activiti tasks with a message sent to an MQTT broker

The project uses Eclipse Paho as a Java MQTT client, and Eclipse Mosquitto for testing.  This is companion code to a series of articles
that are posted on nathanmcminn.com
