import React, {useEffect} from 'react';
import {Button, StyleSheet, Text, View,} from 'react-native';
import {NativeModules, NativeEventEmitter} from "react-native";


const {CalendarModule} = NativeModules;
const eventEmitter = new NativeEventEmitter()

console.log("Start aplikacie")
CalendarModule.createCalendarEvent();
CalendarModule.createCalendarEventCallback(res => console.log(res));


const App = (props) => {

    useEffect(()=>{
        eventEmitter.addListener('EventCount',(eventCount)=>{
            console.log(eventCount);
        });
        return ()=>{
            eventEmitter.removeAllListeners();
        };
        },[] );


    const createCalendarEventPromise = async ()=>{
        try {
          var result =   await CalendarModule.createCalendarPromise();
            console.log(result);
        }catch (e) {
            console.log(e);
        }
        }
    ;
    return (
<View style={styles.container}>
<Text>nazddarr </Text>
    <Button title='Calendar event promise' onPress={createCalendarEventPromise}/>
</View>
);
};

export default App;

const styles = StyleSheet.create({
container:{
flex:1,
alignItems: 'center',
justifyContent:'center'
}
});