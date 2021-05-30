

# Hinweise zum Einlesen
import keras
import matplotlib.pyplot as plt
plt.style.use('default')
from keras import Sequential
from keras.layers import Dense, Activation
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)

train_val = pd.read_csv('titanic_data/train.csv')
# Hinweise um die Accuracy zu berechnen
from sklearn.metrics import accuracy_score


length = int(len(train_val) * 0.8)
train_dataset = train_val[:length]
validation_dataset = train_val[length:]

#print(train_dataset.head())
pd.crosstab(train_dataset['Pclass'], train_dataset['Survived'])

survived_value_counts = train_dataset.groupby('Pclass').Survived.value_counts()
survived_percentage = train_dataset[['Pclass', 'Survived']].groupby(['Pclass'], as_index=False).mean()

print(survived_value_counts)
print(survived_percentage)

model = Sequential()
model.add(Dense(64, activation='relu', input_dim=50)) #input shape of 50
model.add(Dense(28, activation='relu')) #input shape of 50
model.add(Dense(10, activation='softmax'))
model.compile(loss='categorical_crossentropy', optimizer='adadelta', metrics=['accuracy'])

history = model.fit(train_dataset, train_dataset, batch_size=128, epochs=10, verbose=2, validation_data=(validation_dataset, validation_dataset))

plt.title("blood pressure vs age")
plt.xlabel("x (age)")
plt.ylabel("y (sbp)")
plt.plot(history)
plt.show()