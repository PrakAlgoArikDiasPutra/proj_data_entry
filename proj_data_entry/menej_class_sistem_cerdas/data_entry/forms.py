from django import forms
from . models import Pengguna

STATES = (
    ('', 'Choose...'),
    ('DKI', 'Daerah Khusus Ibu Kota'),
    ('DIY', 'Daerah Istimewa Yogyakarta'),
    ('Jabar', 'Jawa Barat'),
)

class AddressForm(forms.Form):...

class PenggunaForm(forms.ModelForm):
    states = forms.ChoiceField(choices=STATES)

    class Meta:
        model = Pengguna
        exclude = ['tanggal_join',]