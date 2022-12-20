import { IonBackButton, IonButton, IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle, IonCol, IonGrid, IonIcon, IonInput, IonItem, IonLabel, IonList, IonRow, IonSelect, IonSelectOption } from '@ionic/react';
import { logIn } from 'ionicons/icons';
import './FormContainer.css';

interface ContainerProps {
    fields?:any[];
    info: object; 
    button: any;
    onsubmit?: any;
  
}


const FormContainer: React.FC<ContainerProps> = (props) => {
    var info:any=props.info;

  return (
    <IonCard>
      <IonCardHeader>
        <IonCardTitle>{info.title}</IonCardTitle>
        <IonCardSubtitle>{info.subtitle}</IonCardSubtitle>
      </IonCardHeader>
      <form onSubmit={props.onsubmit}>
      <IonCardContent>
        <IonList>
        {props.fields?.map((element,index)=>{
          if(element.type!=="select"){
            return (
              

                <IonItem key={index} fill="solid">
                  <IonLabel position="floating">{element.label}</IonLabel>
                  <IonInput type={element.type} defaultValue={element.defaultValue}  name={element.name} placeholder={element.placeholder}></IonInput>
                </IonItem>
            );}else{
              return(

                  <IonItem key={index} fill="solid">
                  <IonLabel position="floating">{element.label}</IonLabel>
                  <IonSelect placeholder={element.placeholder}>
                    {
                      element.data?.map((e:any,index:any)=>{
                        return (<IonSelectOption key={index} value={e.value}>{e.label}</IonSelectOption>);
                      })
                    }
                    
                  </IonSelect>
                  </IonItem>
              );
            }
        })}


        </IonList>
      <IonButton expand='block' type='submit'>
        <IonIcon slot="end" icon={props.button.icon}></IonIcon>
        {props.button.label}
      </IonButton>
      </IonCardContent>
      </form>
    </IonCard>

  );
};

export default FormContainer;
