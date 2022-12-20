import { IonButton, IonIcon, IonInput, IonItem, IonLabel, IonNote } from '@ionic/react';
import { useState } from 'react';
import ExploreContainer from '../components/ExploreContainer';
import './Tab2.css';
import { logIn } from 'ionicons/icons';


const LoginForm: React.FC = () => {

    const [isValid,setIsValid]=useState<boolean>();
    const [isTouched,setIsTouched]=useState(false);
    const [email,setEmail]=useState('');
    const [passwd,setPasswd]=useState('');

    const validateEmail = (email: string) => {
        return email.match(
          /^(?=.{1,254}$)(?=.{1,64}@)[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
        );
      };

    const validate=(ev:Event)=>{

        const value=(ev.target as HTMLInputElement).value;

        setIsValid(undefined);

        if(value==='')
            return;

        validateEmail(value) !==null ? setIsValid(true):setIsValid(false);
        

    }
    const markTouched=()=>{
        setIsTouched(true);
    }
    
  const login = async (e: React.FormEvent) => {
    e.preventDefault();
    console.log(email);
    console.log(passwd);


    var content = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: ""
    };

    content.body = JSON.stringify({
      email: email,
      pwd: passwd,
    });


    fetch("http://localhost:8080/societes/login", content)
      .then((response) => {
        if (response.status === 400) {
          alert("Mot de passe non identifier");
        } else return response.json();
      })
      .then((json) => {
        if (json.length !== 0) setEmail(json);
        else alert("Mot de passe non identifier");
      });


    
  };
    
  return (
    <form onSubmit={login}>
    
    <IonItem fill="solid" className={`${isValid && 'ion-valid'} ${isValid === false && 'ion-invalid'} ${isTouched && 'ion-touched'}`}>
      <IonLabel position="floating">Email</IonLabel>
      <IonInput type="email" value={email} onIonChange={e=> setEmail(e.detail.value!)}  name='email' onIonInput={(event) => validate(event)} onIonBlur={() => markTouched()}></IonInput>
      <IonNote slot="helper">Enter a valid email</IonNote>
      <IonNote slot="error">Invalid email</IonNote>
    </IonItem>

      <IonItem fill="solid">
        <IonLabel position="floating">Password</IonLabel>
        <IonInput type='password' value={passwd} onIonChange={e=> setPasswd(e.detail.value!)} name='passwd' placeholder="passwd"></IonInput>
      </IonItem>

      <IonButton expand='block' type='submit'>
        <IonIcon slot="end" icon={logIn}></IonIcon>
        Log In
      </IonButton>

    </form>
  );
};

export default LoginForm;
