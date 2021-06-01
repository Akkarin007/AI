
# Hinweise zum Einlesen
import keras
import matplotlib.pyplot as plt
plt.style.use('default')
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)
import seaborn as sns


from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import classification_report,confusion_matrix,accuracy_score
from keras import Sequential
from keras.layers import Dense, Activation


train = pd.read_csv('titanic_data/train.csv')
# Hinweise um die Accuracy zu berechnen
from sklearn.metrics import accuracy_score


#length = int(len(train_val) * 0.8)
#train_dataset = train_val[:length]
#validation_dataset = train_val[length:]

train.drop('Cabin',axis=1,inplace=True)
train['Age'].fillna((train['Age'].median()), inplace=True)

sns.set_style('whitegrid')
sns.countplot(x='Survived',data=train,palette='RdBu_r')



sns.set_style('darkgrid')
sns.countplot(x='Survived',hue='Sex',data=train,palette='ocean')

sns.set_style('whitegrid')
sns.countplot(x='Survived',hue='Pclass',data=train,palette='winter')

sns.distplot(train['Age'].dropna(),kde=False,color='darkred',bins=20)

sns.countplot(x='SibSp',data=train,palette='ocean')

sns.countplot(x='Parch',data=train,palette='ocean')



plt.figure(figsize=(12, 7))
sns.boxplot(x='Pclass',y='Age',data=train,palette='winter')



train.info()

# create the dummy variables and drop one column as there is no need of 2 columns in order to differentiate the values.
sex = pd.get_dummies(train['Sex'],drop_first=True)
# similarly for this colimn as well. If there are n dummy columns, consider n-1
embark = pd.get_dummies(train['Embarked'],drop_first=True)



train.drop(['Sex','Embarked','Name','Ticket'],axis=1,inplace=True)
train = pd.concat([train,sex,embark],axis=1)



train.head()
X = train.drop("Survived",axis=1)
y = train['Survived']
#X_train, X_test, y_train, y_test = train_test_split(train.drop('Survived',axis=1),train['Survived'], test_size=0.20,random_state=5)
X_train, X_test, y_train, y_test = train_test_split(X,y,test_size=0.20,random_state=5)

# create an instance
logmodel = LogisticRegression()
# pass the values and build the model
logmodel.fit(X_train,y_train)



# preditcing the test models
predictions = logmodel.predict(X_test)





print(classification_report(y_test,predictions))

